	public static HibernateProxy deserializeProxy(SerializableProxy serializableProxy) {
		final JavassistLazyInitializer initializer = new JavassistLazyInitializer(
				serializableProxy.getEntityName(),
				serializableProxy.getPersistentClass(),
				serializableProxy.getInterfaces(),
				serializableProxy.getId(),
				resolveIdGetterMethod( serializableProxy ),
				resolveIdSetterMethod( serializableProxy ),
				serializableProxy.getComponentIdType(),
				null,
				ReflectHelper.overridesEquals( serializableProxy.getPersistentClass() )
		);

		final javassist.util.proxy.ProxyFactory factory = buildJavassistProxyFactory(
				serializableProxy.getPersistentClass(),
				serializableProxy.getInterfaces()
		);

		// note: interface is assumed to already contain HibernateProxy.class
		try {
			final Class proxyClass = factory.createClass();
			final HibernateProxy proxy = ( HibernateProxy ) proxyClass.newInstance();
			( (Proxy) proxy ).setHandler( initializer );
			initializer.constructed();
			return proxy;
		}
		catch ( Throwable t ) {
			final String message = LOG.bytecodeEnhancementFailed( serializableProxy.getEntityName() );
			LOG.error( message, t );
			throw new HibernateException( message, t );
		}
	}
