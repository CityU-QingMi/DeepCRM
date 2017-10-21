	protected void beforeQuery() {
		queryParameterBindings.verifyParametersBound( isCallable() );

		assert sessionFlushMode == null;
		assert sessionCacheMode == null;

		if ( flushMode != null ) {
			sessionFlushMode = getProducer().getHibernateFlushMode();
			getProducer().setHibernateFlushMode( flushMode );
		}
		final CacheMode effectiveCacheMode = CacheModeHelper.effectiveCacheMode( cacheStoreMode, cacheRetrieveMode );
		if ( effectiveCacheMode != null ) {
			sessionCacheMode = getProducer().getCacheMode();
			getProducer().setCacheMode( effectiveCacheMode );
		}
	}
