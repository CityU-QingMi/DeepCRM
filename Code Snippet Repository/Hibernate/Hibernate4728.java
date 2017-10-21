	@Override
	public ConcurrentNaturalIdCacheStatisticsImpl getNaturalIdCacheStatistics(String regionName) {
		ConcurrentNaturalIdCacheStatisticsImpl stat = naturalIdCacheStatistics.get( regionName );
		
		if ( stat == null ) {
			if ( sessionFactory == null ) {
				return null;
			}

			final NaturalIdRegionAccessStrategy accessStrategy = sessionFactory.getCache().getNaturalIdCacheRegionAccessStrategy( regionName );
			stat = new ConcurrentNaturalIdCacheStatisticsImpl( accessStrategy.getRegion(), accessStrategy );
			ConcurrentNaturalIdCacheStatisticsImpl previous;
			if ( ( previous = naturalIdCacheStatistics.putIfAbsent( regionName, stat ) ) != null ) {
				stat = previous;
			}
		}

		return stat;
	}
