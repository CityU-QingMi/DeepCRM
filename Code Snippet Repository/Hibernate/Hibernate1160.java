	public StandardQueryCache(
			final SessionFactoryOptions settings,
			final Properties props,
			final UpdateTimestampsCache updateTimestampsCache,
			final String regionName) {
		String regionNameToUse = regionName;
		if ( regionNameToUse == null ) {
			regionNameToUse = StandardQueryCache.class.getName();
		}
		final String prefix = settings.getCacheRegionPrefix();
		if ( prefix != null ) {
			regionNameToUse = prefix + '.' + regionNameToUse;
		}
		LOG.startingQueryCache( regionNameToUse );

		this.cacheRegion = settings.getServiceRegistry().getService( RegionFactory.class ).buildQueryResultsRegion(
				regionNameToUse,
				props
		);
		this.updateTimestampsCache = updateTimestampsCache;
	}
