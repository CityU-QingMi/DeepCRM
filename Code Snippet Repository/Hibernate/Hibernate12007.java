	@Override
	public CollectionRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
		switch ( accessType ) {
			case READ_ONLY: {
				if ( getCacheDataDescription().isMutable() ) {
					LOG.warnf( "read-only cache configured for mutable collection [ %s ]", getName() );
				}
				return new ReadOnlyCollectionRegionAccessStrategy( this );
			}
			case READ_WRITE: {
				return new ReadWriteCollectionRegionAccessStrategy( this );
			}
			case NONSTRICT_READ_WRITE: {
				return new NonstrictReadWriteCollectionRegionAccessStrategy( this );
			}
			case TRANSACTIONAL: {
				return new TransactionalCollectionRegionAccessStrategy( this );
//				throw new UnsupportedOperationException( "doesn't support this access strategy" );
			}
			default: {
				throw new IllegalArgumentException( "unrecognized access strategy type [" + accessType + "]" );
			}
		}
	}
