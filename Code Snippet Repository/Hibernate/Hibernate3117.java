	@Override
	public ScrollableResultsImplementor scrollCustomQuery(CustomQuery customQuery, QueryParameters queryParameters) {
		checkOpenOrWaitingForAutoClose();
//		checkTransactionSynchStatus();

		if ( log.isTraceEnabled() ) {
			log.tracev( "Scroll SQL query: {0}", customQuery.getSQL() );
		}

		CustomLoader loader = getFactory().getQueryPlanCache().getNativeQueryInterpreter().createCustomLoader( customQuery, getFactory() );

		autoFlushIfRequired( loader.getQuerySpaces() );

		dontFlushFromFind++; //stops flush being called multiple times if this method is recursively called
		try {
			return loader.scroll( queryParameters, this );
		}
		finally {
			delayedAfterCompletion();
			dontFlushFromFind--;
		}
	}
