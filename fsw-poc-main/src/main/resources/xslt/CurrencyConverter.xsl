<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

  <xsl:template match="fromNS:currencyConverterRequest" xmlns:fromNS="http://currencyconverter.main.fsw_poc.bva.at/">
    <toNS:convert xmlns:toNS="http://currencyconverter.simulation.fsw_poc.bva.at/">
		<converterRequest>
			<currencyCodeFrom>
				<xsl:value-of select="//currencyCodeFrom" />
			</currencyCodeFrom>
			<currencyCodeTo>
				<xsl:value-of select="//currencyCodeTo" />
			</currencyCodeTo>
		</converterRequest>
    </toNS:convert>
  </xsl:template>

  <xsl:template match="fromNS:convertResponse" xmlns:fromNS="http://currencyconverter.simulation.fsw_poc.bva.at/">
	<toNS:currencyConverterResponse xmlns:toNS="http://currencyconverter.main.fsw_poc.bva.at/">
		<amount>
			<xsl:value-of select="//amount" />
		</amount>
		<currencyCode>
			<xsl:value-of select="//currencyCode" />
		</currencyCode>
	</toNS:currencyConverterResponse>
  </xsl:template>

</xsl:stylesheet>