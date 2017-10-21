	protected AdvancedCache getCache(String regionName, DataType type, CacheDataDescription metadata) {
		if (!manager.cacheExists(regionName)) {
			String templateCacheName = baseConfigurations.get(regionName);
			Configuration configuration = null;
			ConfigurationBuilder builder = new ConfigurationBuilder();
			if (templateCacheName != null) {
				configuration = manager.getCacheConfiguration(templateCacheName);
				if (configuration == null) {
					log.customConfigForRegionNotFound(templateCacheName, regionName, type.key);
				}
				else {
					log.debugf("Region '%s' will use cache template '%s'", regionName, templateCacheName);
					builder.read(configuration);
					configureTransactionManager(builder);
					// do not apply data type overrides to regions that set special cache configuration
				}
			}
			if (configuration == null) {
				configuration = dataTypeConfigurations.get(type);
				if (configuration == null) {
					throw new IllegalStateException("Configuration not defined for type " + type.key);
				}
				builder.read(configuration);
				// overrides for data types are already applied, but we should check custom ones
			}
			ConfigurationBuilder override = configOverrides.get(regionName);
			if (override != null) {
				log.debugf("Region '%s' has additional configuration set through properties.", regionName);
				builder.read(override.build(false));
			}
			if (getCacheKeysFactory() instanceof SimpleCacheKeysFactory) {
				// the keys may not define hashCode/equals correctly (e.g. arrays)
				if (metadata != null && metadata.getKeyType() != null) {
					builder.dataContainer().keyEquivalence(new TypeEquivalance(metadata.getKeyType()));
				}
			}
			if (globalStats != null) {
				builder.jmxStatistics().enabled(globalStats).available(globalStats);
			}
			configuration = builder.build();
			type.validate(configuration);
			manager.defineConfiguration(regionName, configuration);
		}
		final AdvancedCache cache = manager.getCache( regionName ).getAdvancedCache();
		// TODO: not sure if this is needed in recent Infinispan
		if ( !cache.getStatus().allowInvocations() ) {
			cache.start();
		}
		return createCacheWrapper( cache );
	}
