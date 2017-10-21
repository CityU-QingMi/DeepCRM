	@Override
	public void start(SessionFactoryOptions settings, Properties properties) throws CacheException {
		log.debug( "Starting Infinispan region factory" );

		// determine the CacheKeysFactory to use...
		this.cacheKeysFactory = determineCacheKeysFactory( settings, properties );

		try {
			this.settings = settings;
			transactionManagerlookup = createTransactionManagerLookup( settings, properties );
			transactionManager = transactionManagerlookup.getTransactionManager();

			final Enumeration keys = properties.propertyNames();
			while ( keys.hasMoreElements() ) {
				final String key = (String) keys.nextElement();
				int prefixLoc;
				if ( (prefixLoc = key.indexOf( PREFIX )) != -1 ) {
					parseProperty( prefixLoc, key, extractProperty(key, properties));
				}
			}

			defaultConfiguration = loadConfiguration(settings.getServiceRegistry(), DEF_INFINISPAN_CONFIG_RESOURCE);
			manager = createCacheManager(properties, settings.getServiceRegistry());
			if (!manager.getCacheManagerConfiguration().isClustered()) {
				// If we got non-clustered cache manager, use non-clustered (local) configuration as defaults
				// for the data types
				defaultConfiguration = loadConfiguration(settings.getServiceRegistry(), INFINISPAN_CONFIG_LOCAL_RESOURCE);
			}
			defineDataTypeCacheConfigurations();
		}
		catch (CacheException ce) {
			throw ce;
		}
		catch (Throwable t) {
			throw log.unableToStart(t);
		}
	}
