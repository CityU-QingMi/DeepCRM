	@Override
	public NaturalIdRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
		switch ( accessType ) {
			case READ_ONLY:
				return new ReadOnlyNaturalIdRegionAccessStrategy( this );
			case NONSTRICT_READ_WRITE:
				return new NonStrictNaturalIdRegionAccessStrategy( this );
			case READ_WRITE:
				return new ReadWriteNaturalIdRegionAccessStrategy( this );
			case TRANSACTIONAL:
				throw new UnsupportedOperationException( "Implement me!" );
			default:
				throw new UnsupportedOperationException( "Unknown AccessType: " + accessType.name() );
		}
	}
