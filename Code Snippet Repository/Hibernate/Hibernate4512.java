	@Override
	public NativeQuery setResultSetMapping(String name) {
		ResultSetMappingDefinition mapping = getProducer().getFactory().getNamedQueryRepository().getResultSetMappingDefinition( name );
		if ( mapping == null ) {
			throw new MappingException( "Unknown SqlResultSetMapping [" + name + "]" );
		}
		NativeSQLQueryReturn[] returns = mapping.getQueryReturns();
		queryReturns.addAll( Arrays.asList( returns ) );
		return this;
	}
