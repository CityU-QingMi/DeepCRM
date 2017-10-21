	private List doList(
			final SharedSessionContractImplementor session,
			final QueryParameters queryParameters,
			final ResultTransformer forcedResultTransformer)
			throws HibernateException {

		final boolean stats = getFactory().getStatistics().isStatisticsEnabled();
		long startTime = 0;
		if ( stats ) {
			startTime = System.nanoTime();
		}

		List result;
		try {
			result = doQueryAndInitializeNonLazyCollections( session, queryParameters, true, forcedResultTransformer );
		}
		catch (SQLException sqle) {
			throw factory.getJdbcServices().getSqlExceptionHelper().convert(
					sqle,
					"could not execute query",
					getSQLString()
			);
		}

		if ( stats ) {
			final long endTime = System.nanoTime();
			final long milliseconds = TimeUnit.MILLISECONDS.convert( endTime - startTime, TimeUnit.NANOSECONDS );
			getFactory().getStatistics().queryExecuted(
					getQueryIdentifier(),
					result.size(),
					milliseconds
			);
		}

		return result;
	}
