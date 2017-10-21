	protected EmbeddedCacheManager createCacheManager(Properties properties, ServiceRegistry serviceRegistry) {
		if (properties.containsKey(INFINISPAN_USE_SYNCHRONIZATION_PROP)) {
			log.propertyUseSynchronizationDeprecated();
		}
		ConfigurationBuilderHolder cfgHolder;
		String configFile = ConfigurationHelper.extractPropertyValue(INFINISPAN_CONFIG_RESOURCE_PROP, properties);
		if (configFile != null) {
			cfgHolder = loadConfiguration(serviceRegistry, configFile);
		}
		else {
			cfgHolder = defaultConfiguration;
		}

		// We cannot just add the default configurations not defined in provided configuration
		// since WF overrides this method - we have to deal with missing configuration for each cache separately
		String globalStatsStr = extractProperty( INFINISPAN_GLOBAL_STATISTICS_PROP, properties	);
		if ( globalStatsStr != null ) {
			globalStats = Boolean.parseBoolean(globalStatsStr);
		}
		if (globalStats != null) {
			cfgHolder.getGlobalConfigurationBuilder().globalJmxStatistics().enabled(globalStats);
		}

		return createCacheManager(cfgHolder);
	}
