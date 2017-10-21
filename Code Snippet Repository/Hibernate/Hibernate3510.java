	protected LockMode determineFollowOnLockMode(LockOptions lockOptions) {
		final LockMode lockModeToUse = lockOptions.findGreatestLockMode();

		if ( lockOptions.hasAliasSpecificLockModes() ) {
			if ( lockOptions.getLockMode() == LockMode.NONE && lockModeToUse == LockMode.NONE ) {
				return lockModeToUse;
			}
			else {
				LOG.aliasSpecificLockingWithFollowOnLocking( lockModeToUse );
			}
		}
		return lockModeToUse;
	}
