	public SessionFactoryBuilderImpl(MetadataImplementor metadata) {
		this.metadata = metadata;
		this.options = new SessionFactoryOptionsStateStandardImpl( metadata.getMetadataBuildingOptions().getServiceRegistry() );

		if ( metadata.getSqlFunctionMap() != null ) {
			for ( Map.Entry<String, SQLFunction> sqlFunctionEntry : metadata.getSqlFunctionMap().entrySet() ) {
				applySqlFunction( sqlFunctionEntry.getKey(), sqlFunctionEntry.getValue() );
			}
		}
	}
