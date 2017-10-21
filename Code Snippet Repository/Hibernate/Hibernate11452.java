	@Override
	public NaturalIdRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
		checkAccessType( accessType );
		AccessDelegate accessDelegate = createAccessDelegate(accessType);
		if ( accessType == AccessType.READ_ONLY || !getCacheDataDescription().isMutable() ) {
			return new ReadOnlyAccess( this, accessDelegate );
		}
		else {
			return new ReadWriteAccess( this, accessDelegate );
		}
	}
