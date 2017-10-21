	public NativeQueryImpl(
			NamedSQLQueryDefinition queryDef,
			SharedSessionContractImplementor session,
			ParameterMetadata parameterMetadata) {
		super( session, parameterMetadata );

		this.sqlString = queryDef.getQueryString();
		this.callable = queryDef.isCallable();
		this.querySpaces = queryDef.getQuerySpaces();

		if ( queryDef.getResultSetRef() != null ) {
			ResultSetMappingDefinition definition = session.getFactory()
					.getNamedQueryRepository()
					.getResultSetMappingDefinition( queryDef.getResultSetRef() );
			if ( definition == null ) {
				throw new MappingException(
						"Unable to find resultset-ref definition: " +
								queryDef.getResultSetRef()
				);
			}
			this.queryReturns = new ArrayList<>( Arrays.asList( definition.getQueryReturns() ) );
		}
		else if ( queryDef.getQueryReturns() != null && queryDef.getQueryReturns().length > 0 ) {
			this.queryReturns = new ArrayList<>( Arrays.asList( queryDef.getQueryReturns() ) );
		}
		else {
			this.queryReturns = new ArrayList<>();
		}
	}
