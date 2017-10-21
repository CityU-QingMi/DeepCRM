	private void addCacheRegionDefinitions(List<CacheRegionDefinition> cacheRegionDefinitions) {
		if ( cacheRegionDefinitions == null ) {
			return;
		}

		if ( this.cacheRegionDefinitions == null ) {
			this.cacheRegionDefinitions = new ArrayList<CacheRegionDefinition>();
		}
		this.cacheRegionDefinitions.addAll( cacheRegionDefinitions );
	}
