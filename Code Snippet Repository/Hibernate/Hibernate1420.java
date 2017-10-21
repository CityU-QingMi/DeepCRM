	private LockOptions determineLockOptions(LockModeType lockModeType, Integer lockTimeoutHint, Boolean followOnLocking) {

		LockOptions lockOptions = new LockOptions( LockModeConverter.convertToLockMode( lockModeType ) )
				.setFollowOnLocking( followOnLocking );
		if ( lockTimeoutHint != null ) {
			lockOptions.setTimeOut( lockTimeoutHint );
		}

		return lockOptions;
	}
