	@Override
	@SuppressWarnings("")
	public <T> QueryImplementor<T> createQuery(String queryString, Class<T> resultClass) {
		checkOpen();
		checkTransactionSynchStatus();
		delayedAfterCompletion();

		try {
			// do the translation
			final QueryImplementor<T> query = createQuery( queryString );
			resultClassChecking( resultClass, query );
			return query;
		}
		catch ( RuntimeException e ) {
			throw exceptionConverter.convert( e );
		}
	}
