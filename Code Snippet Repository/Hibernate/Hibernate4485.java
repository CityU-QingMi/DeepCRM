	@Override
	@SuppressWarnings("")
	public QueryImplementor setLockMode(LockModeType lockModeType) {
		if ( !LockModeType.NONE.equals( lockModeType ) ) {
			if ( !isSelect() ) {
				throw new IllegalStateException( "Illegal attempt to set lock mode on a non-SELECT query" );
			}
		}
		lockOptions.setLockMode( LockModeTypeHelper.getLockMode( lockModeType ) );
		return this;
	}
