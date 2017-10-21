	private Ehcache getCache(String name) throws CacheException {
		try {
			Ehcache cache = manager.getEhcache( name );
			if ( cache == null ) {
				LOG.unableToFindEhCacheConfiguration( name );
				manager.addCache( name );
				cache = manager.getEhcache( name );
				LOG.debug( "started EHCache region: " + name );
			}
			return cache;
		}
		catch (net.sf.ehcache.CacheException e) {
			throw new CacheException( e );
		}

	}
