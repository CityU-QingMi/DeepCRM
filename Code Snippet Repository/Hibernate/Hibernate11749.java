	@Override
	public CollectionRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
		switch ( accessType ) {
			case READ_ONLY:
				return new ReadOnlyCollectionRegionAccessStrategy( this );
			case NONSTRICT_READ_WRITE:
				return new NonStrictCollectionRegionAccessStrategy( this );
			case READ_WRITE:
				return new ReadWriteCollectionRegionAccessStrategy( this );
			case TRANSACTIONAL:
				throw new UnsupportedOperationException( "Implement me!" );
			default:
				throw new UnsupportedOperationException( "Unknown AccessType: " + accessType.name() );
		}
	}
