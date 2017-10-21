	protected final Object resolveIdentifier(Serializable id, SharedSessionContractImplementor session) throws HibernateException {
		boolean isProxyUnwrapEnabled = unwrapProxy &&
				getAssociatedEntityPersister( session.getFactory() )
						.isInstrumented();

		Object proxyOrEntity = session.internalLoad(
				getAssociatedEntityName(),
				id,
				eager,
				isNullable() && !isProxyUnwrapEnabled
		);

		if ( proxyOrEntity instanceof HibernateProxy ) {
			( (HibernateProxy) proxyOrEntity ).getHibernateLazyInitializer()
					.setUnwrap( isProxyUnwrapEnabled );
		}

		return proxyOrEntity;
	}
