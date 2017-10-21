	private Object lockAndLoad(
			final LoadEvent event,
			final EntityPersister persister,
			final EntityKey keyToLoad,
			final LoadEventListener.LoadType options,
			final SessionImplementor source) {
		SoftLock lock = null;
		final Object ck;
		final EntityRegionAccessStrategy cache = persister.getCacheAccessStrategy();
		if ( persister.hasCache() ) {
			ck = cache.generateCacheKey(
					event.getEntityId(),
					persister,
					source.getFactory(),
					source.getTenantIdentifier()
			);
			lock = persister.getCacheAccessStrategy().lockItem( source, ck, null );
		}
		else {
			ck = null;
		}

		Object entity;
		try {
			entity = load( event, persister, keyToLoad, options );
		}
		finally {
			if ( persister.hasCache() ) {
				cache.unlockItem( source, ck, lock );
			}
		}

		return event.getSession().getPersistenceContext().proxyFor( persister, keyToLoad, entity );
	}
