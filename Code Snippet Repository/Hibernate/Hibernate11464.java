	@Override
	public CacheRpcCommand fromStream(byte commandId, Object[] args, String cacheName) {
		CacheRpcCommand c;
		switch ( commandId ) {
			case CacheCommandIds.EVICT_ALL:
				c = new EvictAllCommand( cacheName, allRegions.get( cacheName ) );
				break;
			case CacheCommandIds.END_INVALIDATION:
				c = new EndInvalidationCommand(cacheName);
				break;
			default:
				throw new IllegalArgumentException( "Not registered to handle command id " + commandId );
		}
		c.setParameters( commandId, args );
		return c;
	}
