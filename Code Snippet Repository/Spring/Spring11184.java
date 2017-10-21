	@Override
	protected Mono<String> resolveUrlPathInternal(String resourceUrlPath,
			List<? extends Resource> locations, ResourceResolverChain chain) {

		String key = RESOLVED_URL_PATH_CACHE_KEY_PREFIX + resourceUrlPath;
		String cachedUrlPath = this.cache.get(key, String.class);

		if (cachedUrlPath != null) {
			if (logger.isTraceEnabled()) {
				logger.trace("Found match: \"" + cachedUrlPath + "\"");
			}
			return Mono.just(cachedUrlPath);
		}

		return chain.resolveUrlPath(resourceUrlPath, locations)
				.doOnNext(resolvedPath -> {
					if (logger.isTraceEnabled()) {
						logger.trace("Putting resolved resource URL path in cache: \"" + resolvedPath + "\"");
					}
					this.cache.put(key, resolvedPath);
				});
	}
