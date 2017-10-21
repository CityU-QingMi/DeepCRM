	@Override
	public HibernateProxy getProxy(
			Serializable id,
			SharedSessionContractImplementor session) throws HibernateException {
		final ByteBuddyInterceptor interceptor = new ByteBuddyInterceptor(
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
			( (ProxyConfiguration) proxy ).$$_hibernate_set_interceptor( interceptor );

			return proxy;
		}
		catch (Throwable t) {
			LOG.error( LOG.bytecodeEnhancementFailed( entityName ), t );
			throw new HibernateException( LOG.bytecodeEnhancementFailed( entityName ), t );
		}
	}
