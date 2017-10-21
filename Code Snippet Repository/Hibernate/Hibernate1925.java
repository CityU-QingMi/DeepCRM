	@Override
	public EntityKey getEntityKey() {
		if ( cachedEntityKey == null ) {
			if ( getId() == null ) {
				throw new IllegalStateException( "cannot generate an EntityKey when id is null.");
			}
			cachedEntityKey = new EntityKey( getId(), getPersister() );
		}
		return cachedEntityKey;
	}
