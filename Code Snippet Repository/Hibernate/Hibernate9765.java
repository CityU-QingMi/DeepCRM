	@Override
	public boolean isRegionCachesEnabled() {
		for ( String name : this.cacheManager.getCacheNames() ) {
			final Cache cache = this.cacheManager.getCache( name );
			if ( cache != null ) {
				if ( cache.isDisabled() ) {
					return false;
				}
			}
		}
		return true;
	}
