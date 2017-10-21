	@Override
	public HibernateProxy getProxy(
			Serializable id,
			SharedSessionContractImplementor session) throws HibernateException {
		final JavassistLazyInitializer initializer = new JavassistLazyInitializer(
				entityName,
				persistentClass,
				interfaces,
				id,
				getIdentifierMethod,
				setIdentifierMethod,
				componentIdType,
				session,
				overridesEquals
		);

		try {
			final HibernateProxy proxy = (HibernateProxy) proxyClass.newInstance();
			( (Proxy) proxy ).setHandler( initializer );
			initializer.constructed();

			return proxy;
		}
		catch (Throwable t) {
			LOG.error( LOG.bytecodeEnhancementFailed( entityName ), t );
			throw new HibernateException( LOG.bytecodeEnhancementFailed( entityName ), t );
		}
	}
