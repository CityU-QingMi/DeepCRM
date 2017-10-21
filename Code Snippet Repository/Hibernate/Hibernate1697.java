	@Override
	public String getForUpdateString(final String aliases, final LockOptions lockOptions) {
		LockMode lockMode = lockOptions.findGreatestLockMode();
		lockOptions.setLockMode( lockMode );

		// not sure why this is sometimes empty
		if ( aliases == null || aliases.isEmpty() ) {
			return getForUpdateString( lockOptions );
		}

		return getForUpdateString( aliases, lockMode, lockOptions.getTimeOut() );
	}
