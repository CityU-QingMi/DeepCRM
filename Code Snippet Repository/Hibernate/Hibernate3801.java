	public FetchStats processFetches(
			FetchSource fetchSource,
			SelectStatementBuilder selectStatementBuilder,
			ReaderCollector readerCollector) {
		final FetchStatsImpl fetchStats = new FetchStatsImpl();

		// if the fetchSource is an entityReference, we should also walk its identifier fetches here...
		//
		// what if fetchSource is a composite fetch (as it would be in the case of a key-many-to-one)?
		if ( EntityReference.class.isInstance( fetchSource ) ) {
			final EntityReference fetchOwnerAsEntityReference = (EntityReference) fetchSource;
			if ( fetchOwnerAsEntityReference.getIdentifierDescription().hasFetches() ) {
				final FetchSource entityIdentifierAsFetchSource = (FetchSource) fetchOwnerAsEntityReference.getIdentifierDescription();
				for ( Fetch fetch : entityIdentifierAsFetchSource.getFetches() ) {
					processFetch(
							selectStatementBuilder,
							fetchSource,
							fetch,
							readerCollector,
							fetchStats
					);
				}
			}
		}

		processFetches( fetchSource, selectStatementBuilder, readerCollector, fetchStats );
		return fetchStats;
	}
