	@Override
	public CollectionRegionAccessStrategy createCollectionRegionAccessStrategy(
			EhcacheCollectionRegion collectionRegion,
			AccessType accessType) {
		switch ( accessType ) {
			case READ_ONLY:
				if ( collectionRegion.getCacheDataDescription().isMutable() ) {
					LOG.readOnlyCacheConfiguredForMutableEntity( collectionRegion.getName() );
				}
				return new ReadOnlyEhcacheCollectionRegionAccessStrategy(
						collectionRegion,
						collectionRegion.getSettings()
				);
			case READ_WRITE:
				return new ReadWriteEhcacheCollectionRegionAccessStrategy(
						collectionRegion,
						collectionRegion.getSettings()
				);
			case NONSTRICT_READ_WRITE:
				return new NonStrictReadWriteEhcacheCollectionRegionAccessStrategy(
						collectionRegion,
						collectionRegion.getSettings()
				);
			case TRANSACTIONAL:
				return new TransactionalEhcacheCollectionRegionAccessStrategy(
						collectionRegion, collectionRegion.getEhcache(), collectionRegion
						.getSettings()
				);
			default:
				throw new IllegalArgumentException( "unrecognized access strategy type [" + accessType + "]" );
		}
	}
