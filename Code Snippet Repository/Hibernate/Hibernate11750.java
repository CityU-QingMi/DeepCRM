	@Override
	public EntityRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
		throwIfAccessTypeUnsupported( accessType );
		switch ( accessType ) {
			case READ_ONLY:
				return new ReadOnlyEntityRegionAccessStrategy( this );
			case NONSTRICT_READ_WRITE:
				return new NonStrictEntityRegionAccessStrategy( this );
			case READ_WRITE:
				return new ReadWriteEntityRegionAccessStrategy( this );
			case TRANSACTIONAL:
				return createTransactionalEntityRegionAccessStrategy();
			default:
				throw new IllegalArgumentException( "Unknown AccessType: " + accessType );
		}
	}
