	public void close() throws HibernateException {
		if ( isClosed ) {
			LOG.trace( "Already closed" );
			return;
		}

		LOG.closing();
		observer.sessionFactoryClosing( this );

		isClosed = true;

		settings.getMultiTableBulkIdStrategy().release( serviceRegistry.getService( JdbcServices.class ), buildLocalConnectionAccess() );

		cacheAccess.close();
		metamodel.close();

		queryPlanCache.cleanup();

		if ( delayedDropAction != null ) {
			delayedDropAction.perform( serviceRegistry );
		}

		SessionFactoryRegistry.INSTANCE.removeSessionFactory(
				uuid,
				name,
				settings.isSessionFactoryNameAlsoJndiName(),
				serviceRegistry.getService( JndiService.class )
		);

		observer.sessionFactoryClosed( this );
		serviceRegistry.destroy();
	}
