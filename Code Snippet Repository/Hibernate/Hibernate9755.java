	@Override
	public void setStatisticsEnabled(boolean flag) {
		if ( flag ) {
			hibernateStats.enableStats();
		}
		else {
			hibernateStats.disableStats();
		}
		statsEnabled.set( flag );
		sendNotification( HibernateStats.CACHE_STATISTICS_ENABLED, flag );
	}
