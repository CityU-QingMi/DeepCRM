	@Deprecated
	default RegionAccessStrategy getSecondLevelCacheRegionAccessStrategy(String regionName) {
		final EntityRegionAccessStrategy entityRegionAccess = getCache().getEntityRegionAccess( regionName );
		if ( entityRegionAccess != null ) {
			return entityRegionAccess;
		}

		final CollectionRegionAccessStrategy collectionRegionAccess = getCache().getCollectionRegionAccess( regionName );
		if ( collectionRegionAccess != null ) {
			return collectionRegionAccess;
		}

		return null;
	}
