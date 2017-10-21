	@Override
	public ConcurrentQueryStatisticsImpl getQueryStatistics(String queryString) {
		ConcurrentQueryStatisticsImpl qs = queryStatistics.get( queryString );
		if ( qs == null ) {
			qs = new ConcurrentQueryStatisticsImpl( queryString );
			ConcurrentQueryStatisticsImpl previous;
			if ( ( previous = queryStatistics.putIfAbsent( queryString, qs ) ) != null ) {
				qs = previous;
			}
		}
		return qs;
	}
