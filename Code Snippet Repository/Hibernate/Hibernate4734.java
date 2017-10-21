	@Override
	public void queryCachePut(String hql, String regionName) {
		queryCachePutCount.getAndIncrement();
		if ( hql != null ) {
			ConcurrentQueryStatisticsImpl qs = getQueryStatistics( hql );
			qs.incrementCachePutCount();
		}
		ConcurrentSecondLevelCacheStatisticsImpl slcs = getSecondLevelCacheStatistics( regionName );
		slcs.incrementPutCount();
	}
