	@SuppressWarnings("")
	public Iterator performIterate(
			QueryParameters queryParameters,
			EventSource session) throws HibernateException {
		if ( traceEnabled ) {
			LOG.tracev( "Iterate: {0}", getSourceQuery() );
			queryParameters.traceParameters( session.getFactory() );
		}
		if ( translators.length == 0 ) {
			return EmptyIterator.INSTANCE;
		}

		final boolean many = translators.length > 1;
		Iterator[] results = null;
		if ( many ) {
			results = new Iterator[translators.length];
		}

		Iterator result = null;
		for ( int i = 0; i < translators.length; i++ ) {
			result = translators[i].iterate( queryParameters, session );
			if ( many ) {
				results[i] = result;
			}
		}

		return many ? new JoinedIterator( results ) : result;
	}
