	public static Object unproxy(Object proxy) {
		if ( proxy instanceof HibernateProxy ) {
			HibernateProxy hibernateProxy = (HibernateProxy) proxy;
			LazyInitializer initializer = hibernateProxy.getHibernateLazyInitializer();
			return initializer.getImplementation();
		}
		else {
			return proxy;
		}
	}
