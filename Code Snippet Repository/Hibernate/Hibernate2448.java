	private EntityCopyObserver createEntityCopyObserver(SessionFactoryImplementor sessionFactory) {
		final ServiceRegistry serviceRegistry = sessionFactory.getServiceRegistry();
		if ( entityCopyObserverStrategy == null ) {
			final ConfigurationService configurationService
					= serviceRegistry.getService( ConfigurationService.class );
			entityCopyObserverStrategy = configurationService.getSetting(
					AvailableSettings.MERGE_ENTITY_COPY_OBSERVER,
					new ConfigurationService.Converter<String>() {
						@Override
						public String convert(Object value) {
							return value.toString();
						}
					},
					EntityCopyNotAllowedObserver.SHORT_NAME
			);
			LOG.debugf( "EntityCopyObserver strategy: %s", entityCopyObserverStrategy );
		}
		final StrategySelector strategySelector = serviceRegistry.getService( StrategySelector.class );
		return strategySelector.resolveStrategy( EntityCopyObserver.class, entityCopyObserverStrategy );
	}
