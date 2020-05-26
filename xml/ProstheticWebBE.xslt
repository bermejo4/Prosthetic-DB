<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" />

	<xsl:template match="/">
		<html>
			<head>
				<title>Biomedical Engineer Transformation from Xml</title>
				<link rel="stylesheet" href="ProstheticDbStyle.css"></link>
			</head>
			<body>
				<div id="container">
					<xsl:for-each select="Biomedicals/Biomedical">
						<div id="boundsHosp">
							<p>
								<b><img class="image3" src="imagesForHtml/hospital.png"> Name: </img></b>
								<xsl:value-of select="name" />
							</p>
							<p>
								<b><img class="image3" src="imagesForHtml/location.png">Last Name: </img></b>
								<xsl:value-of select="lastname" />
							</p>
							<p>
								<b><img class="image3" src="imagesForHtml/telephone.png"> Telephone: </img></b>
								<xsl:value-of select="telephone" />
							</p>
							<p>
								<b>
									<img class="image3" src="imagesForHtml/doctor.png"> Prosthetics: </img> <br /> 
									<table>
										<tr>
											<th>Type</th>
											<th>Material</th>
											<th>Dimension</th>
											<th>Price</th>
											<th>Failures</th>
											<th>Availability</th>
										</tr>
										<xsl:for-each select="Prosthetic">
										<tr>
											<td>
												<p>
													<xsl:value-of select="@type" />
												</p>
											</td>
											<td>
												<p>
													<xsl:value-of select="material" />
												</p>
											</td>
											<td>
												<p>
													<xsl:value-of select="dimensions" />
												</p>
											</td>
											<td>
												<p>
													<xsl:value-of select="price" />
												</p>
											</td>
											<td>
												<p>
													<xsl:value-of select="failures" />
												</p>
											</td>
											<td>
												<p>
													<xsl:value-of select="available" />
												</p>
											</td>												
										</tr>
										</xsl:for-each>
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