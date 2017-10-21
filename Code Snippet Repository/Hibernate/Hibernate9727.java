	@Override
	public NaturalIdRegionAccessStrategy createNaturalIdRegionAccessStrategy(
			EhcacheNaturalIdRegion naturalIdRegion,
			AccessType accessType) {
		switch ( accessType ) {
			case READ_ONLY:
				if ( naturalIdRegion.getCacheDataDescription().isMutable() ) {
					LOG.readOnlyCacheConfiguredForMutableEntity( naturalIdRegion.getName() );
				}
				return new ReadOnlyEhcacheNaturalIdRegionAccessStrategy(
						naturalIdRegion,
						naturalIdRegion.getSettings()
				);
			case READ_WRITE:
				return new ReadWriteEhcacheNaturalIdRegionAccessStrategy(
						naturalIdRegion,
						naturalIdRegion.getSettings()
				);
			case NONSTRICT_READ_WRITE:
				return new NonStrictReadWriteEhcacheNaturalIdRegionAccessStrategy(
						naturalIdRegion,
						naturalIdRegion.getSettings()
				);
			case TRANSACTIONAL:
				return new TransactionalEhcacheNaturalIdRegionAccessStrategy(
						naturalIdRegion, naturalIdRegion.getEhcache(), naturalIdRegion
						.getSettings()
				);
			default:
				throw new IllegalArgumentException( "unrecognized access strategy type [" + accessType + "]" );
		}
	}
