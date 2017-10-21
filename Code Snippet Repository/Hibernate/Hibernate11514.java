		@Override
      protected EmbeddedCacheManager createCacheManager(Properties properties, ServiceRegistry serviceRegistry) throws CacheException {
         EmbeddedCacheManager m;
			if (providedManager != null)
				m = providedManager;
			else
				m = super.createCacheManager(properties, serviceRegistry);
			// since data type cache configuration templates are defined when cache manager is created,
			// we have to use hooks and set the configuration before the whole factory starts
			if (afterCacheManagerCreated != null) {
				afterCacheManagerCreated.accept(this, m);
			}
			return m;
		}
