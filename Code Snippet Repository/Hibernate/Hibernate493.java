	@SuppressWarnings("")
	public static boolean isInitialized(Object proxy) {
		if ( proxy instanceof HibernateProxy ) {
			return !( (HibernateProxy) proxy ).getHibernateLazyInitializer().isUninitialized();
		}
		else if ( proxy instanceof PersistentCollection ) {
			return ( (PersistentCollection) proxy ).wasInitialized();
		}
		else {
			return true;
		}
	}
