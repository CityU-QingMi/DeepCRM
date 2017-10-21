	@Override
	protected LockMode[] getLockModes(LockOptions lockOptions) {
		if ( lockOptions == null ) {
			return defaultLockModes;
		}

		if ( lockOptions.getAliasLockCount() == 0
				&& ( lockOptions.getLockMode() == null || LockMode.NONE.equals( lockOptions.getLockMode() ) ) ) {
			return defaultLockModes;
		}

		// unfortunately this stuff can't be cached because
		// it is per-invocation, not constant for the
		// QueryTranslator instance

		LockMode[] lockModesArray = new LockMode[entityAliases.length];
		for ( int i = 0; i < entityAliases.length; i++ ) {
			LockMode lockMode = lockOptions.getEffectiveLockMode( entityAliases[i] );
			if ( lockMode == null ) {
				//NONE, because its the requested lock mode, not the actual!
				lockMode = LockMode.NONE;
			}
			lockModesArray[i] = lockMode;
		}

		return lockModesArray;
	}
