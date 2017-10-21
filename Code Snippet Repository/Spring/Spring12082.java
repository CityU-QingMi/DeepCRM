	@Nullable
	protected Integer lookupCacheSeconds(String urlPath) {
		// Direct match?
		Integer cacheSeconds = this.cacheMappings.get(urlPath);
		if (cacheSeconds != null) {
			return cacheSeconds;
		}
		// Pattern match?
		for (String registeredPath : this.cacheMappings.keySet()) {
			if (this.pathMatcher.match(registeredPath, urlPath)) {
				return this.cacheMappings.get(registeredPath);
			}
		}
		return null;
	}
