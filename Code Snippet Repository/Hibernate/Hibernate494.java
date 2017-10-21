	public static Class getClass(Object proxy) {
		if ( proxy instanceof HibernateProxy ) {
			return ( (HibernateProxy) proxy ).getHibernateLazyInitializer()
					.getImplementation()
					.getClass();
		}
		else {
			return proxy.getClass();
		}
	}
