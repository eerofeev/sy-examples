#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
PROJECT_DIR=`readlink -f $SCRIPT_DIR/../../../..`
ENV_DIR=`readlink -f $SCRIPT_DIR/../environment`

function checkSuccess {
	RETVAL=$?
	if [ "$RETVAL" != "0" ]
	then
        	echo "==== FAILURE code $RETVAL ===="
		cd $(echo $CURDIR)
       		exit 1
	fi
}

function maven {
	if [ "$SKIP_TESTS" == "true" ]
	then
        mvn clean install -Dmaven.test.skip=true
		checkSuccess
	else
		mvn clean install
		checkSuccess
	fi
}

function setSystemProperty {
	PROPERTY_NAME=$1
	PROPERTY_VALUE=$2
	if [ "$PROPERTY_VALUE"!="" ]
	then
		echo "Trying to setup $PROPERTY_NAME=$PROPERTY_VALUE system property"
		$JBOSS_HOME/bin/jboss-cli.sh --connect --controller=localhost:$ADMIN_PORT --command="/system-property=$PROPERTY_NAME:remove"	
		$JBOSS_HOME/bin/jboss-cli.sh --connect --controller=localhost:$ADMIN_PORT --command="/system-property=$PROPERTY_NAME:add(value=$PROPERTY_VALUE)"
	fi
}

function printHelp {
	echo ""
	echo "================================================================================================================="
	echo "usage: $0 --env=<Path to env> [--all] [--common] [--main] [--simulation] [--ejb] [--connectors] [--configure] [--skip-tests] [--redeploy] [--help]"
	echo "-----------------------------------------------------------------------------------------------------------------"
	echo "Parameters"
	echo "-----------------------------------------------------------------------------------------------------------------"
	echo "--env: name of environment configuration"
	echo "--all: perform all build and installation steps (excluding jboss configuration)"
	echo "--<component>: perform build and deployment of corresponding prototype component (common, main, simulation)"
	echo "--configure: perform necessary jboss and environment configuration steps (e.g. queues configuration, path to motion, create conax dirs)"
	echo "--ejb: build and deploy ejb invoker"
	echo "--skip-tests: use maven with skip test"
	echo "================================================================================================================="
	echo ""
	exit 0
}

BASEDIR=$(dirname $0)

# processing of inputed command
if [ $# == 0 ]
then
	printHelp
fi

export ALL=false

while test $# -gt 0; do
case "$1" in
                --help)
                        printHelp
			;;
		--all)
                        export ALL=true
                        shift
                        ;;
		--env*)
                        ENV_NAME=`echo $1 | sed -e 's/^[^=]*=//g'`
                        export ENV="${ENV_DIR}/${ENV_NAME}.env"
                        shift
			;;
		--main)
			export MAIN=true
			shift
                        ;;
        --common)
			export COMMON=true
			shift
                        ;;
		--simulation)
			export SIMULATION=true
			shift
                        ;;
               --connectors)
                        export CONNECTORS=true
                        shift
                        ;;
               --ejb)
                        export EJB=true
                        shift
                        ;;
                --redeploy)
                        export REDEPLOY=true
                        shift
                        ;;

		--configure)
			export CONFIGURE=true
			shift
                        ;;
		--skip-tests)
			export SKIP_TESTS=true
			shift
                        ;;
                *)
printHelp
;;
                        
        esac
done

if [ "$ENV" == "" ]
then
	echo "ENV is not set"
	printHelp
fi

if [[ ! -f $ENV ]]; then
    	echo "$ENV not exists"
	printHelp
fi

source $ENV

if [ "$JBOSS_HOME" == "" ]
then
	echo "JBOSS_HOME is not set"
	printHelp
fi

if [ "$ADMIN_PORT" == "" ]
then
	echo "ADMIN_PORT is not set"
	printHelp
fi

echo "==== START BUILD AND DEPLOY ===="

if [ "$CONFIGURE" == "true" ]
then
	echo "CONFIGURATION SCRIPTS"
fi

CURDIR=$(pwd)

cd $BASEDIR
BASEDIR=`pwd`

##########################
#
# Build connectors
#
##########################
if [ "$CONNECTORS" == "true" ] || [ "$ALL" == "true" ]
then
        cd $PROJECT_DIR/jboss-connectors
        maven
	checkSuccess
fi


##########################
#
# Build common
#
##########################
if [ "$COMMON" == "true" ] || [ "$ALL" == "true" ]
then
	cd $PROJECT_DIR/fsw-poc-common
	maven
	checkSuccess
fi

##########################
#
# Build sy simulation
#
##########################
if [ "$SIMULATION" == "true" ] || [ "$ALL" == "true" ]
then	
	cd $PROJECT_DIR/fsw-poc-simulation
	maven
	
	$JBOSS_HOME/bin/jboss-cli.sh --connect --controller=localhost:$ADMIN_PORT --command="undeploy fsw-poc-simulation-0.0.1-SNAPSHOT.war"
	$JBOSS_HOME/bin/jboss-cli.sh --connect --controller=localhost:$ADMIN_PORT --command="deploy target/fsw-poc-simulation-0.0.1-SNAPSHOT.war"
	checkSuccess
fi

##########################
#
# Build sy ejb
#
##########################
if [ "$EJB" == "true" ] || [ "$ALL" == "true" ]
then	
	cd $PROJECT_DIR/fsw-poc-eap5-ejb
	maven
	
	$JBOSS_HOME/bin/jboss-cli.sh --connect --controller=localhost:$ADMIN_PORT --command="undeploy fsw-poc-eap5-ejb-0.0.1-SNAPSHOT.war"
	$JBOSS_HOME/bin/jboss-cli.sh --connect --controller=localhost:$ADMIN_PORT --command="deploy target/fsw-poc-eap5-ejb-0.0.1-SNAPSHOT.war"
	checkSuccess
fi

##########################
#
# Build sy main
#
##########################
if [ "$MAIN" == "true" ] || [ "$ALL" == "true" ]
then
	cd $PROJECT_DIR/fsw-poc-main
	maven
	
	$JBOSS_HOME/bin/jboss-cli.sh --connect --controller=localhost:$ADMIN_PORT --command="undeploy fsw-poc-main-0.0.1-SNAPSHOT.war"
	$JBOSS_HOME/bin/jboss-cli.sh --connect --controller=localhost:$ADMIN_PORT --command="deploy target/fsw-poc-main-0.0.1-SNAPSHOT.war"
	checkSuccess
fi

##########################
#
# Build sy main
#
##########################
if [ "$REDEPLOY" == "true" ]
then
        cd $PROJECT_DIR/fsw-poc-main
	$JBOSS_HOME/bin/jboss-cli.sh --connect --controller=localhost:$ADMIN_PORT --command="undeploy fsw-poc-main-0.0.1-SNAPSHOT.war"
        $JBOSS_HOME/bin/jboss-cli.sh --connect --controller=localhost:$ADMIN_PORT --command="deploy target/fsw-poc-main-0.0.1-SNAPSHOT.war"
        checkSuccess
fi


echo "==== FINISHED ===="
