	public static CacheModeType fromCacheMode(CacheMode cacheMode) {
		if ( null == cacheMode ) {
			return null;
		}

		switch ( cacheMode ) {
			case NORMAL: {
				return NORMAL;
			}
			case GET: {
				return GET;
			}
			case PUT: {
				return PUT;
			}
			case REFRESH: {
				return REFRESH;
			}
			case IGNORE: {
				return IGNORE;
			}
			default: {
				throw new IllegalArgumentException( "Unrecognized CacheMode : " + cacheMode );
			}
		}
	}
