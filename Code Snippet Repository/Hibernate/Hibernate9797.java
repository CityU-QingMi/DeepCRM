	@Override
	protected Map getMapFromCacheEntry(final Object entry) {
		final Map map;
		if ( entry.getClass()
				.getName()
				.equals( "org.hibernate.cache.ehcache.internal.strategy.AbstractReadWriteEhcacheAccessStrategy$Item" ) ) {
			map = ItemValueExtractor.getValue( entry );
		}
		else {
			map = (Map) entry;
		}
		return map;
	}
