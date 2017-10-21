	private Document loadUrl(URL xmlUrl) {
		final String resourceName = xmlUrl.toExternalForm();
		try {
			URLConnection conn = xmlUrl.openConnection();
			conn.setUseCaches( false ); //avoid JAR locking on Windows and Tomcat
			try {
				InputStream inputStream = conn.getInputStream();
				try {
					final InputSource inputSource = new InputSource( inputStream );
					try {
						DocumentBuilder documentBuilder = documentBuilderFactory().newDocumentBuilder();
						try {
							Document document = documentBuilder.parse( inputSource );
							validate( document );
							return document;
						}
						catch (SAXException e) {
							throw new PersistenceException( "Unexpected error parsing [" + resourceName + "]", e );
						}
						catch (IOException e) {
							throw new PersistenceException( "Unexpected error parsing [" + resourceName + "]", e );
						}
					}
					catch (ParserConfigurationException e) {
						throw new PersistenceException( "Unable to generate javax.xml.parsers.DocumentBuilder instance", e );
					}
				}
				finally {
					try {
						inputStream.close();
					}
					catch (Exception ignored) {
					}
				}
			}
			catch (IOException e) {
				throw new PersistenceException( "Unable to obtain input stream from [" + resourceName + "]", e );
			}
		}
		catch (IOException e) {
			throw new PersistenceException( "Unable to access [" + resourceName + "]", e );
		}
	}
