	@Override
	protected LockMode[] getLockModes(LockOptions lockOptions) {

		// unfortunately this stuff can't be cached because
		// it is per-invocation, not constant for the
		// QueryTranslator instance
		HashMap nameLockOptions = new HashMap();
		if ( lockOptions == null ) {
			lockOptions = LockOptions.NONE;
		}

		if ( lockOptions.getAliasLockCount() > 0 ) {
			Iterator iter = lockOptions.getAliasLockIterator();
			while ( iter.hasNext() ) {
				Map.Entry me = (Map.Entry) iter.next();
				nameLockOptions.put(
						getAliasName( (String) me.getKey() ),
						me.getValue()
				);
			}
		}
		LockMode[] lockModesArray = new LockMode[names.length];
		for ( int i = 0; i < names.length; i++ ) {
			LockMode lm = (LockMode) nameLockOptions.get( names[i] );
			//if ( lm == null ) lm = LockOptions.NONE;
			if ( lm == null ) {
				lm = lockOptions.getLockMode();
			}
			lockModesArray[i] = lm;
		}
		return lockModesArray;
	}
