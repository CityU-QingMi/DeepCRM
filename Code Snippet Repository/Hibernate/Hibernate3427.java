	public static CacheMode effectiveCacheMode(CacheStoreMode storeMode, CacheRetrieveMode retrieveMode) {
		if ( storeMode == null && retrieveMode == null ) {
			return null;
		}

		if ( storeMode == null ) {
			storeMode = DEFAULT_STORE_MODE;
		}
		if ( retrieveMode == null ) {
			retrieveMode = DEFAULT_RETRIEVE_MODE;
		}

		final boolean get = ( CacheRetrieveMode.USE == retrieveMode );

		switch ( storeMode ) {
			case USE: {
				return get ? CacheMode.NORMAL : CacheMode.PUT;
			}
			case REFRESH: {
				// really (get == true) here is a bit of an invalid combo...
				return CacheMode.REFRESH;
			}
			case BYPASS: {
				return get ? CacheMode.GET : CacheMode.IGNORE;
			}
			default: {
				throw new IllegalStateException( "huh? :)" );
			}
		}
	}
