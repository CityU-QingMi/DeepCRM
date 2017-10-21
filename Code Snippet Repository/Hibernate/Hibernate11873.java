	private SDOGeometryTypeDescriptor mkSdoGeometryTypeDescriptor(ServiceRegistry serviceRegistry) {
		final ConfigurationService cfgService = serviceRegistry.getService( ConfigurationService.class );
		final StrategySelector strategySelector = serviceRegistry.getService( StrategySelector.class );

		final ConnectionFinder connectionFinder = strategySelector.resolveStrategy(
				ConnectionFinder.class,
				cfgService.getSetting(
						HibernateSpatialConfigurationSettings.CONNECTION_FINDER,
						String.class,
						"org.geolatte.geom.codec.db.oracle.DefaultConnectionFinder"
				)
		);

		log.connectionFinder( connectionFinder.getClass().getCanonicalName() );

		return new SDOGeometryTypeDescriptor(
				new OracleJDBCTypeFactory(
						connectionFinder
				)
		);
	}
