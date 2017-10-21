	@Override
	protected Map getMapFromCacheEntry(final Object entry) {
		final Map map;
		if ( "org.hibernate.cache.ehcache.internal.strategy.AbstractReadWriteEhcacheAccessStrategy$Item".equals(
				entry.getClass()
						.getName()
		) ) {
			try {
				Field field = entry.getClass().getDeclaredField( "value" );
				field.setAccessible( true );
				map = (Map) field.get( entry );
			}
			catch ( NoSuchFieldException e ) {
				throw new RuntimeException( e );
			}
			catch ( IllegalAccessException e ) {
				throw new RuntimeException( e );
			}
		}
		else {
			map = (Map) entry;
		}
		return map;
	}
