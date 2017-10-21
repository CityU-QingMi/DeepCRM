	@Override
	public String[] getTerracottaHibernateCacheRegionNames() {
		final ArrayList<String> rv = new ArrayList<String>();
		for ( String name : cacheManager.getCacheNames() ) {
			final Cache cache = cacheManager.getCache( name );
			if ( cache != null ) {
				if ( cache.getCacheConfiguration().isTerracottaClustered() ) {
					rv.add( name );
				}
			}
		}
		return rv.toArray( new String[rv.size()] );
	}
