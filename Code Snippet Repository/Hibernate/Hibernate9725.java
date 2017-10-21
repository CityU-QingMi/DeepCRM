	@Override
	public EntityRegionAccessStrategy createEntityRegionAccessStrategy(
			EhcacheEntityRegion entityRegion,
			AccessType accessType) {
		switch ( accessType ) {
			case READ_ONLY:
				if ( entityRegion.getCacheDataDescription().isMutable() ) {
					LOG.readOnlyCacheConfiguredForMutableEntity( entityRegion.getName() );
				}
				return new ReadOnlyEhcacheEntityRegionAccessStrategy( entityRegion, entityRegion.getSettings() );
			case READ_WRITE:
				return new ReadWriteEhcacheEntityRegionAccessStrategy( entityRegion, entityRegion.getSettings() );

			case NONSTRICT_READ_WRITE:
				return new NonStrictReadWriteEhcacheEntityRegionAccessStrategy(
						entityRegion,
						entityRegion.getSettings()
				);

			case TRANSACTIONAL:
				return new TransactionalEhcacheEntityRegionAccessStrategy(
						entityRegion,
						entityRegion.getEhcache(),
						entityRegion.getSettings()
				);
			default:
				throw new IllegalArgumentException( "unrecognized access strategy type [" + accessType + "]" );

		}

	}
