	@Override
	public ReplicableCommand fromStream(byte commandId, Object[] args) {
		ReplicableCommand c;
		switch ( commandId ) {
			case CacheCommandIds.BEGIN_INVALIDATION:
				c = new BeginInvalidationCommand();
				break;
			default:
				throw new IllegalArgumentException( "Not registered to handle command id " + commandId );
		}
		c.setParameters( commandId, args );
		return c;
	}
