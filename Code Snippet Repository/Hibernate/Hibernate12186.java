	public Schema getSchema(String schemaResource) throws XmlParsingException {
		Schema schema = SCHEMA_CACHE.get( schemaResource );

		if ( schema != null ) {
			return schema;
		}

		schema = loadSchema( schemaResource );
		Schema previous = SCHEMA_CACHE.putIfAbsent( schemaResource, schema );

		return previous != null ? previous : schema;
	}
