	private Mono<Resource> resolveVersionedResource(@Nullable ServerWebExchange exchange,
			String requestPath, List<? extends Resource> locations, ResourceResolverChain chain) {

		VersionStrategy versionStrategy = getStrategyForPath(requestPath);
		if (versionStrategy == null) {
			return Mono.empty();
		}

		String candidate = versionStrategy.extractVersion(requestPath);
		if (StringUtils.isEmpty(candidate)) {
			if (logger.isTraceEnabled()) {
				logger.trace("No version found in path \"" + requestPath + "\"");
			}
			return Mono.empty();
		}

		String simplePath = versionStrategy.removeVersion(requestPath, candidate);
		if (logger.isTraceEnabled()) {
			logger.trace("Extracted version from path, re-resolving without version: \"" + simplePath + "\"");
		}

		return chain.resolveResource(exchange, simplePath, locations)
				.filterWhen(resource -> versionStrategy.getResourceVersion(resource)
						.map(actual -> {
							if (candidate.equals(actual)) {
								if (logger.isTraceEnabled()) {
									logger.trace("Resource matches extracted version [" + candidate + "]");
								}
								return true;
							}
							else {
								if (logger.isTraceEnabled()) {
									logger.trace("Potential resource found for \"" + requestPath + "\", " +
											"but version [" + candidate + "] does not match");
								}
								return false;
							}
						}))
				.map(resource -> new FileNameVersionedResource(resource, candidate));
	}
