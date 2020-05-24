
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" />

	<xsl:template match="/">
		<html>
			<head>
				<title>Prosthetic Transformation from Xml</title>
				<link rel="stylesheet" href="ProstheticDbStyle.css"></link>
			</head>
			<body>
				<div id="container">
					<xsl:for-each select="prosthetics/prosthetic">
						<div id="boundsPros">
							<p>
								<b><img class="image3" src="imagesForHtml/type.png"> Type: </img> </b>
								<xsl:value-of select="@type" />
							</p>
							<p>
								<b><img class="image3" src="imagesForHtml/material.png"> Material: </img></b>
								<xsl:value-of select="material" />
							</p>
							<p>
								<b><img class="image3" src="imagesForHtml/coin.png"> Price: </img></b>
								<xsl:value-of select="price" /> €
							</p>
							<p>
								<b><img class="image3" src="imagesForHtml/dimensions.png"> Dimensions: </img></b>
								<xsl:value-of select="dimensions" /> cm
							</p>
							<p>
								<b><img class="image3" src="imagesForHtml/Failures.png"> Failures: </img></b>
								<xsl:value-of select="failures" />
							</p>
							<p>
								<b><img class="image3" src="imagesForHtml/available.png"> Availability: </img></b>
								<xsl:value-of select="available" />
							</p>
							<p>
								<b>
									<img class="image3" src="imagesForHtml/patient.png"> Patient: </img> <br /> 
									<table>
										<tr>
											<th>Name</th>
											<th>Last Name</th>
										</tr>
										<tr>
											<td>
												<p>
													<xsl:value-of select="patient/name " />
												</p>
											</td>
											<td>
												<p>
													<xsl:value-of select="patient/lastname " />
												</p>
											</td>
										</tr>
									</table>
								</b>
							</p>
						</div>
					</xsl:for-each>
				</div>
			</body>
		</html>



	</xsl:template>
</xsl:stylesheet>