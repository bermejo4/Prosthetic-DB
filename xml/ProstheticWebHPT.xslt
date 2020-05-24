<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" indent="yes" />

	<xsl:template match="/">
		<html>
			<head>
				<title>Hospital Transformation from Xml</title>
				<link rel="stylesheet" href="ProstheticDbStyle.css"></link>
			</head>
			<body>
				<div id="container">
					<xsl:for-each select="hospitals/hospital">
						<div id="boundsHosp">
							<p>
								<b><img class="image3" src="imagesForHtml/hospital.png"> Hospital Name: </img></b>
								<xsl:value-of select="@name" />
							</p>
							<p>
								<b><img class="image3" src="imagesForHtml/location.png">Location: </img></b>
								<xsl:value-of select="address" />
							</p>
							<p>
								<b><img class="image3" src="imagesForHtml/telephone.png"> Telephone: </img></b>
								<xsl:value-of select="telephone" />
							</p>
							<p>
								<b>
									<img class="image3" src="imagesForHtml/doctor.png"> Doctors: </img> <br /> 
									<table>
										<tr>
											<th>Name</th>
											<th>Last Name</th>
											<th>Telephone</th>
											<th>Department</th>
										</tr>
										<xsl:for-each select="doctors">
										<tr>
											<td>
												<p>
													<xsl:value-of select="name " />
												</p>
											</td>
											<td>
												<p>
													<xsl:value-of select="lastname" />
												</p>
											</td>
											<td>
												<p>
													<xsl:value-of select="telephone" />
												</p>
											</td>
											<td>
												<p>
													<xsl:value-of select="department" />
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