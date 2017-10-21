	@Override
	public JtaPlatformResolver initiateService(Map configurationValues, ServiceRegistryImplementor registry) {
		final Object setting = configurationValues.get( AvailableSettings.JTA_PLATFORM_RESOLVER );
		final JtaPlatformResolver resolver = registry.getService( StrategySelector.class )
				.resolveStrategy( JtaPlatformResolver.class, setting );
		if ( resolver == null ) {
			log.debugf( "No JtaPlatformResolver was specified, using default [%s]", StandardJtaPlatformResolver.class.getName() );
			return StandardJtaPlatformResolver.INSTANCE;
		}
		return resolver;
	}
