	private void defineDataTypeCacheConfigurations() {
		for ( DataType type : DataType.values() ) {
			String cacheName = baseConfigurations.get(type.key);
			if (cacheName == null) {
				cacheName = type.defaultCacheName;
			}
			Configuration configuration = manager.getCacheConfiguration(cacheName);
			ConfigurationBuilder builder;
			if (configuration == null) {
				log.debugf("Cache configuration not found for %s", type);
				if (!cacheName.equals(type.defaultCacheName)) {
					log.customConfigForTypeNotFound(cacheName, type.key);
				}
				builder = defaultConfiguration.getNamedConfigurationBuilders().get(type.defaultCacheName);
				if (builder == null) {
					throw new IllegalStateException("Generic data types must have default configuration, none found for " + type);
				}
			}
			else {
				builder = new ConfigurationBuilder().read(configuration);
			}
			ConfigurationBuilder override = configOverrides.get( type.key );
			if (override != null) {
				builder.read(override.build(false));
			}
			builder.template(true);
			configureTransactionManager( builder );
			dataTypeConfigurations.put(type, builder.build());
		}
	}
