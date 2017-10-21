	private void processFetch(
			SelectStatementBuilder selectStatementBuilder,
			FetchSource fetchSource,
			Fetch fetch,
			ReaderCollector readerCollector,
			FetchStatsImpl fetchStats) {

		// process fetch even if it is not join fetched
		if ( EntityFetch.class.isInstance( fetch ) ) {
			final EntityFetch entityFetch = (EntityFetch) fetch;
			processEntityFetch(
					selectStatementBuilder,
					fetchSource,
					entityFetch,
					readerCollector,
					fetchStats
			);
		}
		else if ( CollectionAttributeFetch.class.isInstance( fetch ) ) {
			final CollectionAttributeFetch collectionFetch = (CollectionAttributeFetch) fetch;
			processCollectionFetch(
					selectStatementBuilder,
					fetchSource,
					collectionFetch,
					readerCollector,
					fetchStats
			);
		}
		else {
			// could also be a CompositeFetch, we ignore those here
			// but do still need to visit their fetches...
			if ( FetchSource.class.isInstance( fetch ) ) {
				processFetches(
						(FetchSource) fetch,
						selectStatementBuilder,
						readerCollector,
						fetchStats
				);
			}
		}
	}
