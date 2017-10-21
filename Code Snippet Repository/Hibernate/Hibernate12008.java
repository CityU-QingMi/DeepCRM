	@Override
	public EntityRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
		switch ( accessType ) {
			case READ_ONLY:
				if ( getCacheDataDescription().isMutable() ) {
					LOG.warnf( "read-only cache configured for mutable entity [ %s ]", getName() );
				}
				return new ReadOnlyEntityRegionAccessStrategy( this );
			case READ_WRITE:
				return new ReadWriteEntityRegionAccessStrategy( this );
			case NONSTRICT_READ_WRITE:
				return new NonstrictReadWriteEntityRegionAccessStrategy( this );
			case TRANSACTIONAL:
//				throw new UnsupportedOperationException( "doesn't support this access strategy" );
				return new TransactionalEntityRegionAccessStrategy( this );

			default:
				throw new IllegalArgumentException( "unrecognized access strategy type [" + accessType + "]" );
		}

	}
