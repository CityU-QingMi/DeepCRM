	public void start(SessionFactoryOptions settings, Properties properties) throws CacheException {
		cacheManagerName = properties.getProperty(DualNodeTest.NODE_ID_PROP);

		EmbeddedCacheManager existing = getCacheManager(cacheManagerName);
		locallyAdded = (existing == null);

		if (locallyAdded) {
			delegate.start(settings, properties);
			cacheManagers.put(cacheManagerName, delegate.getCacheManager());
		} else {
			delegate.setCacheManager(existing);
		}
	}
