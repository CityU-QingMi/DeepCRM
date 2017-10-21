	@Override
	protected Mono<String> resolveUrlPathInternal(String resourceUrlPath,
			List<? extends Resource> locations, ResourceResolverChain chain) {

		return chain.resolveUrlPath(resourceUrlPath, locations)
				.flatMap(baseUrl -> {
					if (StringUtils.hasText(baseUrl)) {
						VersionStrategy strategy = getStrategyForPath(resourceUrlPath);
						if (strategy == null) {
							return Mono.just(baseUrl);
						}
						if (logger.isTraceEnabled()) {
							logger.trace("Getting the original resource to determine version " +
									"for path \"" + resourceUrlPath + "\"");
						}
						return chain.resolveResource(null, baseUrl, locations)
								.flatMap(resource -> strategy.getResourceVersion(resource)
										.map(version -> {
											if (logger.isTraceEnabled()) {
												logger.trace("Determined version [" + version + "] for " + resource);
											}
											return strategy.addVersion(baseUrl, version);
										}));
					}
					return Mono.empty();
				});
	}
