	@Override
	public void queryCacheHit(String hql, String regionName) {
		queryCacheHitCount.getAndIncrement();
		if ( hql != null ) {
			ConcurrentQueryStatisticsImpl qs = getQueryStatistics( hql );
			qs.incrementCacheHitCount();
		}
		ConcurrentSecondLevelCacheStatisticsImpl slcs = getSecondLevelCacheStatistics(
				regionName
		);
		slcs.incrementHitCount();
	}
