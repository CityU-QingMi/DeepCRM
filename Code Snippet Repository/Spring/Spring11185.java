	@Override
	public Mono<Resource> transform(ServerWebExchange exchange, Resource resource,
			ResourceTransformerChain transformerChain) {

		Resource cachedResource = this.cache.get(resource, Resource.class);
		if (cachedResource != null) {
			if (logger.isTraceEnabled()) {
				logger.trace("Found match: " + cachedResource);
			}
			return Mono.just(cachedResource);
		}

		return transformerChain.transform(exchange, resource)
				.doOnNext(transformed -> {
					if (logger.isTraceEnabled()) {
						logger.trace("Putting transformed resource in cache: " + transformed);
					}
					this.cache.put(resource, transformed);
				});
	}
