	protected void buildSessionFactory(Consumer<Configuration> configurationAdapter) {
		// for now, build the configuration to get all the property settings
		configuration = constructAndConfigureConfiguration();
		if ( configurationAdapter != null ) {
			configurationAdapter.accept(configuration);
		}
		BootstrapServiceRegistry bootRegistry = buildBootstrapServiceRegistry();
		serviceRegistry = buildServiceRegistry( bootRegistry, configuration );
		// this is done here because Configuration does not currently support 4.0 xsd
		afterConstructAndConfigureConfiguration( configuration );
		sessionFactory = ( SessionFactoryImplementor ) configuration.buildSessionFactory( serviceRegistry );
		afterSessionFactoryBuilt();
	}
