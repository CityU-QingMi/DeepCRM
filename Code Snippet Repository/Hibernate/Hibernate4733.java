	@Override
	public void queryCacheMiss(String hql, String regionName) {
		queryCacheMissCount.getAndIncrement();
		if ( hql != null ) {
			ConcurrentQueryStatisticsImpl qs = getQueryStatistics( hql );
			qs.incrementCacheMissCount();
		}
		ConcurrentSecondLevelCacheStatisticsImpl slcs = getSecondLevelCacheStatistics(
				regionName
		);
		slcs.incrementMissCount();
	}
