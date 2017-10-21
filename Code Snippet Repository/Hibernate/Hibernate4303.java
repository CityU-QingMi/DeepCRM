	public static javassist.util.proxy.ProxyFactory buildJavassistProxyFactory(
			final Class persistentClass,
			final Class[] interfaces) {
		javassist.util.proxy.ProxyFactory factory = new javassist.util.proxy.ProxyFactory() {
			@Override
			protected ClassLoader getClassLoader() {
				return persistentClass.getClassLoader();
			}
		};
		factory.setSuperclass( interfaces.length == 1 ? persistentClass : null );
		factory.setInterfaces( interfaces );
		factory.setFilter( EXCLUDE_FILTER );
		return factory;
	}
