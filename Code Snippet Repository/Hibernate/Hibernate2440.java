	private Object proxyOrLoad(
			final LoadEvent event,
			final EntityPersister persister,
			final EntityKey keyToLoad,
			final LoadEventListener.LoadType options) {

		if ( traceEnabled ) {
			LOG.tracev(
					"Loading entity: {0}",
					MessageHelper.infoString( persister, event.getEntityId(), event.getSession().getFactory() )
			);
		}

		// this class has no proxies (so do a shortcut)
		if ( !persister.hasProxy() ) {
			return load( event, persister, keyToLoad, options );
		}

		final PersistenceContext persistenceContext = event.getSession().getPersistenceContext();

		// look for a proxy
		Object proxy = persistenceContext.getProxy( keyToLoad );
		if ( proxy != null ) {
			return returnNarrowedProxy( event, persister, keyToLoad, options, persistenceContext, proxy );
		}

		if ( options.isAllowProxyCreation() ) {
			return createProxyIfNecessary( event, persister, keyToLoad, options, persistenceContext );
		}

		// return a newly loaded object
		return load( event, persister, keyToLoad, options );
	}
