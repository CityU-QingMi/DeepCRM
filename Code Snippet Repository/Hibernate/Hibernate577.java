	public CacheRegionDefinition(
			CacheRegionType cacheType,
			String role,
			String usage,
			String region,
			boolean cacheLazy) {
		this.regionType = cacheType;
		this.role = role;
		this.usage = usage;
		this.region = region;
		this.cacheLazy = cacheLazy;
	}
