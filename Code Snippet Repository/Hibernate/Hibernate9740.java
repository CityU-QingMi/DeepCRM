	public static Configuration loadAndCorrectConfiguration(URL url) {
		final Configuration config = ConfigurationFactory.parseConfiguration( url );
		
		// EHC-875 / HHH-6576
		if ( config == null ) {
			return null;
		}

		if ( config.getDefaultCacheConfiguration() != null
				&& config.getDefaultCacheConfiguration().isTerracottaClustered() ) {
			setupHibernateTimeoutBehavior(
					config.getDefaultCacheConfiguration()
							.getTerracottaConfiguration()
							.getNonstopConfiguration()
			);
		}

		for ( CacheConfiguration cacheConfig : config.getCacheConfigurations().values() ) {
			if ( cacheConfig.isTerracottaClustered() ) {
				setupHibernateTimeoutBehavior( cacheConfig.getTerracottaConfiguration().getNonstopConfiguration() );
			}
		}
		return config;
	}
