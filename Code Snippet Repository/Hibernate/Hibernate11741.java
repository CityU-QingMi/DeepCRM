	protected void amendConfiguration(ConfigurationBuilderHolder holder) {
		holder.getGlobalConfigurationBuilder().globalJmxStatistics().allowDuplicateDomains(true);
		TransportConfigurationBuilder transport = holder.getGlobalConfigurationBuilder().transport();
		transport.nodeName(TestResourceTracker.getNextNodeName());
		transport.clusterName(TestResourceTracker.getCurrentTestName());
		// minimize number of threads using unlimited cached thread pool
		transport.remoteCommandThreadPool().threadPoolFactory(CachedThreadPoolExecutorFactory.create());
		transport.transportThreadPool().threadPoolFactory(CachedThreadPoolExecutorFactory.create());
		for (Map.Entry<String, ConfigurationBuilder> cfg : holder.getNamedConfigurationBuilders().entrySet()) {
			amendCacheConfiguration(cfg.getKey(), cfg.getValue());
		}
		// disable simple cache for testing as we need to insert interceptors
		if (!pendingPutsSimple) {
			holder.getNamedConfigurationBuilders().get(InfinispanRegionFactory.DEF_PENDING_PUTS_RESOURCE).simpleCache(false);
		}
	}
