	@Override
	public Object read(ResultSet resultSet, ResultSetProcessingContext context) throws SQLException {
		final EntityReferenceProcessingState processingState = getIdentifierResolutionContext( context );

		final EntityKey entityKey = processingState.getEntityKey();
		final Object entityInstance = context.getProcessingState( entityReturn ).getEntityInstance();

		if ( context.shouldReturnProxies() ) {
			final Object proxy = context.getSession().getPersistenceContext().proxyFor(
					entityReturn.getEntityPersister(),
					entityKey,
					entityInstance
			);
			if ( proxy != entityInstance ) {
				( (HibernateProxy) proxy ).getHibernateLazyInitializer().setImplementation( proxy );
				return proxy;
			}
		}

		return entityInstance;
	}
