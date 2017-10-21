	private SchemaExport createSchemaExport(Class[] annotatedClasses) {
		final MetadataSources metadataSources = new MetadataSources( ssr );

		for ( Class c : annotatedClasses ) {
			metadataSources.addAnnotatedClass( c );
		}
		metadata = (MetadataImplementor) metadataSources.buildMetadata();
		metadata.validate();
		SchemaExport schemaExport = new SchemaExport();
		schemaExport.setHaltOnError( true )
				.setFormat( false );

		return schemaExport;
	}
