	@Override
	public Mono<String> resolveUrlPath(String resourceUrlPath, List<? extends Resource> locations,
			ResourceResolverChain chain) {

		if (logger.isTraceEnabled()) {
			logger.trace("Resolving public URL for resource path \"" + resourceUrlPath + "\"");
		}

		return resolveUrlPathInternal(resourceUrlPath, locations, chain);
	}
