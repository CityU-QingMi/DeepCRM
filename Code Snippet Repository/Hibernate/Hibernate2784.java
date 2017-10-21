	@Override
	public QueryTranslator createQueryTranslator(
			String queryIdentifier,
			String queryString,
			Map filters,
			SessionFactoryImplementor factory,
			EntityGraphQueryHint entityGraphQueryHint) {
		if ( entityGraphQueryHint != null ) {
			throw new QueryException( "EntityGraphs cannot be applied queries using the classic QueryTranslator!" );
		}
		return new QueryTranslatorImpl( queryIdentifier, queryString, filters, factory );
	}
