	public ConcurrentCollectionStatisticsImpl getCollectionStatistics(String role) {
		ConcurrentCollectionStatisticsImpl cs = collectionStatistics.get( role );
		if ( cs == null ) {
			cs = new ConcurrentCollectionStatisticsImpl( role );
			ConcurrentCollectionStatisticsImpl previous;
			if ( ( previous = collectionStatistics.putIfAbsent( role, cs ) ) != null ) {
				cs = previous;
			}
		}
		return cs;
	}
