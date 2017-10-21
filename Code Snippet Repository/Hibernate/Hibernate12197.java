	private void loadEntityMappings(Collection<String> mappingFileNames) {
		for ( String mappingFile : mappingFileNames ) {
			InputStream stream = xmlParserHelper.getInputStreamForResource( mappingFile );
			if ( stream == null ) {
				continue;
			}
			EntityMappings mapping = null;
			try {
				Schema schema = xmlParserHelper.getSchema( ORM_SCHEMA );
				mapping = xmlParserHelper.getJaxbRoot( stream, EntityMappings.class, schema );
			}
			catch (XmlParsingException e) {
				context.logMessage(
						Diagnostic.Kind.WARNING, "Unable to parse " + mappingFile + ": " + e.getMessage()
				);
			}
			if ( mapping != null ) {
				entityMappings.add( mapping );
			}

			try {
				stream.close();
			}
			catch (IOException e) {
				// eat it
			}
		}
	}
