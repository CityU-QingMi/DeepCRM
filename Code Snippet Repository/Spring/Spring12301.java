	@Override
	protected String resolveUrlPathInternal(String resourceUrlPath,
			List<? extends Resource> locations, ResourceResolverChain chain) {

		String baseUrl = chain.resolveUrlPath(resourceUrlPath, locations);
		if (StringUtils.hasText(baseUrl)) {
			VersionStrategy versionStrategy = getStrategyForPath(resourceUrlPath);
			if (versionStrategy == null) {
				return baseUrl;
			}
			if (logger.isTraceEnabled()) {
				logger.trace("Getting the original resource to determine version for path \"" + resourceUrlPath + "\"");
			}
			Resource resource = chain.resolveResource(null, baseUrl, locations);
			Assert.state(resource != null, "Unresolvable resource");
			String version = versionStrategy.getResourceVersion(resource);
			if (logger.isTraceEnabled()) {
				logger.trace("Determined version [" + version + "] for " + resource);
			}
			return versionStrategy.addVersion(baseUrl, version);
		}
		return baseUrl;
	}
