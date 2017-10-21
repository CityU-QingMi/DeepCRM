	private Schema loadSchema(String schemaName) throws XmlParsingException {
		Schema schema = null;
		URL schemaUrl = this.getClass().getClassLoader().getResource( schemaName );
		if ( schemaUrl == null ) {
			return schema;
		}

		SchemaFactory sf = SchemaFactory.newInstance( javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI );
		try {
			schema = sf.newSchema( schemaUrl );
		}
		catch ( SAXException e ) {
			throw new XmlParsingException( "Unable to create schema for " + schemaName + ": " + e.getMessage(), e );
		}
		return schema;
	}
