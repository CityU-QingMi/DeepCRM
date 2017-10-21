	@Override
	public QueryImplementor createQuery(String queryString) {
		checkOpen();
		checkTransactionSynchStatus();
		delayedAfterCompletion();

		try {
			final QueryImpl query = new QueryImpl(
					this,
					getQueryPlan( queryString, false ).getParameterMetadata(),
					queryString
			);
			query.setComment( queryString );
			applyQuerySettingsAndHints( query );
			return query;
		}
		catch (RuntimeException e) {
			throw exceptionConverter.convert( e );
		}
	}
