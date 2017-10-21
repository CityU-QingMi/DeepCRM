	public static boolean isPropertyInitialized(Object proxy, String propertyName) {
		final Object entity;
		if ( proxy instanceof HibernateProxy ) {
			final LazyInitializer li = ( (HibernateProxy) proxy ).getHibernateLazyInitializer();
			if ( li.isUninitialized() ) {
				return false;
			}
			else {
				entity = li.getImplementation();
			}
		}
		else {
			entity = proxy;
		}

		if ( entity instanceof PersistentAttributeInterceptable ) {
			PersistentAttributeInterceptor interceptor = ( (PersistentAttributeInterceptable) entity ).$$_hibernate_getInterceptor();
			if ( interceptor != null && interceptor instanceof LazyAttributeLoadingInterceptor ) {
				return ( (LazyAttributeLoadingInterceptor) interceptor ).isAttributeLoaded( propertyName );
			}
		}

		return true;
	}
