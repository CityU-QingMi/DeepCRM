	@Override
	public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
		Collection<String> cacheNames = getCacheNames(context);
		if (cacheNames == null) {
			return Collections.emptyList();
		}
		else {
			Collection<Cache> result = new ArrayList<>();
			for (String cacheName : cacheNames) {
				Cache cache = getCacheManager().getCache(cacheName);
				if (cache == null) {
					throw new IllegalArgumentException("Cannot find cache named '" +
							cacheName + "' for " + context.getOperation());
				}
				result.add(cache);
			}
			return result;
		}
	}
