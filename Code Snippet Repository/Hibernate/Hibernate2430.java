	private Object loadFromSecondLevelCache(
			final LoadEvent event,
			final EntityPersister persister,
			final EntityKey entityKey) {

		final SessionImplementor source = event.getSession();
		final boolean useCache = persister.hasCache()
				&& source.getCacheMode().isGetEnabled()
				&& event.getLockMode().lessThan( LockMode.READ );

		if ( !useCache ) {
			// we can't use cache here
			return null;
		}

		final Object ce = getFromSharedCache( event, persister, source );

		if ( ce == null ) {
			// nothing was found in cache
			return null;
		}

		return processCachedEntry( event, persister, ce, source, entityKey );
	}
