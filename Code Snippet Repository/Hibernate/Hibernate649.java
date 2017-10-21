	public InFlightMetadataCollectorImpl(
			MetadataBuildingOptions options,
			TypeResolver typeResolver) {
		this.uuid = UUID.randomUUID();
		this.options = options;
		this.typeResolver = typeResolver;

		this.identifierGeneratorFactory = options.getServiceRegistry().getService( MutableIdentifierGeneratorFactory.class );

		for ( Map.Entry<String, SQLFunction> sqlFunctionEntry : options.getSqlFunctions().entrySet() ) {
			if ( sqlFunctionMap == null ) {
				// we need this to be a ConcurrentHashMap for the one we ultimately pass along to the SF
				// but is this the reference that gets passed along?
				sqlFunctionMap = new ConcurrentHashMap<String, SQLFunction>( 16, .75f, 1 );
			}
			sqlFunctionMap.put( sqlFunctionEntry.getKey(), sqlFunctionEntry.getValue() );
		}

		for ( AuxiliaryDatabaseObject auxiliaryDatabaseObject : options.getAuxiliaryDatabaseObjectList() ) {
			getDatabase().addAuxiliaryDatabaseObject( auxiliaryDatabaseObject );
		}

	}
