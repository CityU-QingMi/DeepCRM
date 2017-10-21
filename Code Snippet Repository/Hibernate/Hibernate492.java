	public static void initialize(Object proxy) throws HibernateException {
		if ( proxy == null ) {
			return;
		}

		if ( proxy instanceof HibernateProxy ) {
			( (HibernateProxy) proxy ).getHibernateLazyInitializer().initialize();
		}
		else if ( proxy instanceof PersistentCollection ) {
			( (PersistentCollection) proxy ).forceInitialization();
		}
	}
