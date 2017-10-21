	public int performExecuteUpdate(QueryParameters queryParameters, SharedSessionContractImplementor session)
			throws HibernateException {
		if ( traceEnabled ) {
			LOG.tracev( "Execute update: {0}", getSourceQuery() );
			queryParameters.traceParameters( session.getFactory() );
		}
		if ( translators.length != 1 ) {
			LOG.splitQueries( getSourceQuery(), translators.length );
		}
		int result = 0;
		for ( QueryTranslator translator : translators ) {
			result += translator.executeUpdate( queryParameters, session );
		}
		return result;
	}
