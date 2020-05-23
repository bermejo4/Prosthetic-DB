
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
		<html>
			<head>
    		<title>Prosthetic Transformation from xml</title>
    		<link rel="stylesheet" href="ProstheticDbStyle.css"></link>
			</head>
			<body>
				<p><b>Type: </b><xsl:value-of select = "/prosthetic/@type" /></p>
				<p><b>Material: </b> <xsl:value-of select = "/prosthetic/material" /></p>
				<p><b>Price: </b> <xsl:value-of select = "/prosthetic/price" /></p>
				<p><b>Dimensions: </b> <xsl:value-of select = "/prosthetic/dimensions" /></p>
				<p><b>Failures: </b> <xsl:value-of select = "/prosthetic/failures" /></p>
				<p><b>Availability: </b> <xsl:value-of select = "/prosthetic/available" /></p>
				<p><b>Patient: 
						<table border ="1">
						<tr>
						<th>Name</th> 
						<th>Last Name</th>
						</tr>
						<tr>
						
							<td> <xsl:value-of select = "/name "/> </td>
							<td> <xsl:value-of select = "/lastname "/> </td> 	
						
						</tr>
					
					
						</table>
				</b></p>			
				</body>		
		</html>



</xsl:template>
</xsl:stylesheet>