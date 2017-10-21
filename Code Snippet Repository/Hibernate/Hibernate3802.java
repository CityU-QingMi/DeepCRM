	private void processFetches(
			FetchSource fetchSource,
			SelectStatementBuilder selectStatementBuilder,
			ReaderCollector readerCollector,
			FetchStatsImpl fetchStats) {
		for ( Fetch fetch : fetchSource.getFetches() ) {
			processFetch(
					selectStatementBuilder,
					fetchSource,
					fetch,
					readerCollector,
					fetchStats
			);
		}
	}
