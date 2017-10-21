	@Override
	public void evictQueryRegions() {
		evictDefaultQueryRegion();

		if ( CollectionHelper.isEmpty( queryCaches ) ) {
			return;
		}
		if ( LOG.isDebugEnabled() ) {
			LOG.debug( "Evicting cache of all query regions." );
		}

		queryCaches.values().forEach( QueryCache::clear );
	}
