	@Override
	public void setRegionCachesEnabled(final boolean flag) {
		for ( String name : this.cacheManager.getCacheNames() ) {
			final Cache cache = this.cacheManager.getCache( name );
			if ( cache != null ) {
				cache.setDisabled( !flag );
			}
		}
		sendNotification( CACHE_ENABLED, flag );
	}
