	@Override
	public NativeQueryImplementor createNativeQuery(String sqlString, Class resultClass) {
		checkOpen();
		checkTransactionSynchStatus();
		delayedAfterCompletion();

		try {
			NativeQueryImplementor query = createNativeQuery( sqlString );
			handleNativeQueryResult(query, resultClass);
			return query;
		}
		catch ( RuntimeException he ) {
			throw exceptionConverter.convert( he );
		}
	}
