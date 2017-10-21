	@Override
	@SuppressWarnings( {""})
	public JtaPlatform initiateService(Map configurationValues, ServiceRegistryImplementor registry) {
		final Object setting = configurationValues.get( AvailableSettings.JTA_PLATFORM );
		final JtaPlatform platform = registry.getService( StrategySelector.class ).resolveStrategy( JtaPlatform.class, setting );
		if ( platform == null ) {
			LOG.debugf( "No JtaPlatform was specified, checking resolver" );
			return registry.getService( JtaPlatformResolver.class ).resolveJtaPlatform( configurationValues, registry );
		}
		return platform;
	}
