	@Override
	protected Mono<Resource> resolveResourceInternal(@Nullable ServerWebExchange exchange,
			String requestPath, List<? extends Resource> locations, ResourceResolverChain chain) {

		return chain.resolveResource(exchange, requestPath, locations)
				.map(resource -> {
					if (exchange == null || isGzipAccepted(exchange)) {
						try {
							Resource gzipped = new GzippedResource(resource);
							if (gzipped.exists()) {
								resource = gzipped;
							}
						}
						catch (IOException ex) {
							logger.trace("No gzipped resource for [" + resource.getFilename() + "]", ex);
						}
					}
					return resource;
				});
	}
