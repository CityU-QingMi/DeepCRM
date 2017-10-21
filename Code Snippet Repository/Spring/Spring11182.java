	@Override
	protected Mono<Resource> resolveResourceInternal(@Nullable ServerWebExchange exchange,
			String requestPath, List<? extends Resource> locations, ResourceResolverChain chain) {

		String key = computeKey(exchange, requestPath);
		Resource cachedResource = this.cache.get(key, Resource.class);

		if (cachedResource != null) {
			if (logger.isTraceEnabled()) {
				logger.trace("Found match: " + cachedResource);
			}
			return Mono.just(cachedResource);
		}

		return chain.resolveResource(exchange, requestPath, locations)
				.doOnNext(resource -> {
					if (logger.isTraceEnabled()) {
						logger.trace("Putting resolved resource in cache: " + resource);
					}
					this.cache.put(key, resource);
				});
	}
