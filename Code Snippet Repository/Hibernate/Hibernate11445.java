	protected void prepareForVersionedEntries() {
		if (strategy != null) {
			assert strategy == Strategy.VERSIONED_ENTRIES;
			return;
		}

		replaceCommonInterceptors();
		replaceExpirationManager();

		cache.removeInterceptor(CallInterceptor.class);
		VersionedCallInterceptor tombstoneCallInterceptor = new VersionedCallInterceptor(this, metadata.getVersionComparator());
		cache.getComponentRegistry().registerComponent(tombstoneCallInterceptor, VersionedCallInterceptor.class);
		List<CommandInterceptor> interceptorChain = cache.getInterceptorChain();
		cache.addInterceptor(tombstoneCallInterceptor, interceptorChain.size());

		strategy = Strategy.VERSIONED_ENTRIES;
	}
