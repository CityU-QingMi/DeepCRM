	@Override
	public List listCustomQuery(CustomQuery customQuery, QueryParameters queryParameters) {
		checkOpenOrWaitingForAutoClose();
//		checkTransactionSynchStatus();

		if ( log.isTraceEnabled() ) {
			log.tracev( "SQL query: {0}", customQuery.getSQL() );
		}

		CustomLoader loader = getFactory().getQueryPlanCache().getNativeQueryInterpreter().createCustomLoader( customQuery, getFactory() );

		autoFlushIfRequired( loader.getQuerySpaces() );

		dontFlushFromFind++;
		boolean success = false;
		try {
			List results = loader.list( this, queryParameters );
			success = true;
			return results;
		}
		finally {
			dontFlushFromFind--;
			delayedAfterCompletion();
			afterOperation( success );
		}
	}
