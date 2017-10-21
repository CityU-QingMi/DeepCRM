	@Deprecated
	default Region getSecondLevelCacheRegion(String regionName) {
		final EntityRegionAccessStrategy entityRegionAccess = getCache().getEntityRegionAccess( regionName );
		if ( entityRegionAccess != null ) {
			return entityRegionAccess.getRegion();
		}

		final CollectionRegionAccessStrategy collectionRegionAccess = getCache().getCollectionRegionAccess( regionName );
		if ( collectionRegionAccess != null ) {
			return collectionRegionAccess.getRegion();
		}

		return null;
	}
