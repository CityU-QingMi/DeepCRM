	@Override
	protected LockMode determineFollowOnLockMode(LockOptions lockOptions) {
		final LockMode lockModeToUse = lockOptions.findGreatestLockMode();

		if ( lockOptions.getAliasLockCount() > 1 ) {
			// > 1 here because criteria always uses alias map for the root lock mode (under 'this_')
			LOG.aliasSpecificLockingWithFollowOnLocking( lockModeToUse );
		}

		return lockModeToUse;
	}
