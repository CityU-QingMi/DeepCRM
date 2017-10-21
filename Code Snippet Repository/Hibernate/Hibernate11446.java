	private void prepareForTombstones() {
		if (strategy != null) {
			assert strategy == Strategy.TOMBSTONES;
			return;
		}
		Configuration configuration = cache.getCacheConfiguration();
		if (configuration.eviction().maxEntries() >= 0) {
			log.evictionWithTombstones();
		}

		replaceCommonInterceptors();
		replaceExpirationManager();

		cache.removeInterceptor(CallInterceptor.class);
		TombstoneCallInterceptor tombstoneCallInterceptor = new TombstoneCallInterceptor(this);
		cache.getComponentRegistry().registerComponent(tombstoneCallInterceptor, TombstoneCallInterceptor.class);
		List<CommandInterceptor> interceptorChain = cache.getInterceptorChain();
		cache.addInterceptor(tombstoneCallInterceptor, interceptorChain.size());

		strategy = Strategy.TOMBSTONES;
	}
