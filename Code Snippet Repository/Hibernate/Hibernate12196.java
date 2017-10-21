	private Persistence getPersistence() {
		Persistence persistence = null;
		String persistenceXmlLocation = context.getPersistenceXmlLocation();
		InputStream stream = xmlParserHelper.getInputStreamForResource( persistenceXmlLocation );
		if ( stream == null ) {
			return null;
		}

		try {
			Schema schema = xmlParserHelper.getSchema( PERSISTENCE_SCHEMA );
			persistence = xmlParserHelper.getJaxbRoot( stream, Persistence.class, schema );
		}
		catch (XmlParsingException e) {
			context.logMessage(
					Diagnostic.Kind.WARNING, "Unable to parse persistence.xml: " + e.getMessage()
			);
		}

		try {
			stream.close();
		}
		catch (IOException e) {
			// eat it
		}

		return persistence;
	}
