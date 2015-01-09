package snippet;

public class Snippet {
	<xsd:schema xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:tns="http://testwss.examples.gepardec.com/" attributeFormDefault="unqualified" elementFormDefault="qualified" targetNamespace="http://testwss.examples.gepardec.com/">
	  <xsd:element name="sayHello" type="tns:sayHello"/>
	  <xsd:complexType name="sayHello">
	    <xsd:sequence>
	      <xsd:element minOccurs="0" name="string" type="xsd:string"/>
	    </xsd:sequence>
	  </xsd:complexType>
	  <xsd:element name="sayHelloResponse" type="tns:sayHelloResponse"/>
	  <xsd:complexType name="sayHelloResponse">
	    <xsd:sequence>
	      <xsd:element minOccurs="0" name="string" type="xsd:string"/>
	    </xsd:sequence>
	  </xsd:complexType>
	</xsd:schema>
}

