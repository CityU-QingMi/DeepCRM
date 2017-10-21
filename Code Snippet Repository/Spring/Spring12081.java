	@Nullable
	protected CacheControl lookupCacheControl(String urlPath) {
		// Direct match?
		CacheControl cacheControl = this.cacheControlMappings.get(urlPath);
		if (cacheControl != null) {
			return cacheControl;
		}
		// Pattern match?
		for (String registeredPath : this.cacheControlMappings.keySet()) {
			if (this.pathMatcher.match(registeredPath, urlPath)) {
				return this.cacheControlMappings.get(registeredPath);
			}
		}
		return null;
	}
