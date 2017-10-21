	public static LoadState isLoaded(Object reference) {
		if ( reference instanceof HibernateProxy ) {
			final boolean isInitialized = !( (HibernateProxy) reference ).getHibernateLazyInitializer().isUninitialized();
			return isInitialized ? LoadState.LOADED : LoadState.NOT_LOADED;
		}
		else if ( reference instanceof PersistentAttributeInterceptable ) {
			boolean isInitialized = isInitialized( (PersistentAttributeInterceptable) reference );
			return isInitialized ? LoadState.LOADED : LoadState.NOT_LOADED;
		}
		else if ( reference instanceof PersistentCollection ) {
			final boolean isInitialized = ( (PersistentCollection) reference ).wasInitialized();
			return isInitialized ? LoadState.LOADED : LoadState.NOT_LOADED;
		}
		else {
			return LoadState.UNKNOWN;
		}
	}
