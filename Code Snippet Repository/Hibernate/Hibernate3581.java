	@Override
	protected LockMode[] getLockModes(LockOptions lockOptions) {
		final String[] entityAliases = getAliases();
		if ( entityAliases == null ) {
			return null;
		}
		final int size = entityAliases.length;
		LockMode[] lockModesArray = new LockMode[size];
		for ( int i = 0; i < size; i++ ) {
			LockMode lockMode = lockOptions.getAliasSpecificLockMode( entityAliases[i] );
			lockModesArray[i] = lockMode == null ? lockOptions.getLockMode() : lockMode;
		}
		return lockModesArray;
	}
