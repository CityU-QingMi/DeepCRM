	public ConcurrentEntityStatisticsImpl getEntityStatistics(String entityName) {
		ConcurrentEntityStatisticsImpl es = entityStatistics.get( entityName );
		if ( es == null ) {
			es = new ConcurrentEntityStatisticsImpl( entityName );
			ConcurrentEntityStatisticsImpl previous;
			if ( ( previous = entityStatistics.putIfAbsent( entityName, es ) ) != null ) {
				es = previous;
			}
		}
		return es;
	}
