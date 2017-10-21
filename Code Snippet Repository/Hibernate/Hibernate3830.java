	private void checkVersion(
			ResultSet resultSet,
			ResultSetProcessingContext context,
			EntityKey entityKey,
			Object existing) {
		final LockMode requestedLockMode = context.resolveLockMode( entityReference );
		if ( requestedLockMode != LockMode.NONE ) {
			final LockMode currentLockMode = context.getSession().getPersistenceContext().getEntry( existing ).getLockMode();
			final boolean isVersionCheckNeeded = entityReference.getEntityPersister().isVersioned()
					&& currentLockMode.lessThan( requestedLockMode );

			// we don't need to worry about existing version being uninitialized because this block isn't called
			// by a re-entrant load (re-entrant loads *always* have lock mode NONE)
			if ( isVersionCheckNeeded ) {
				//we only check the version when *upgrading* lock modes
				checkVersion(
						context.getSession(),
						resultSet,
						entityReference.getEntityPersister(),
						entityReferenceAliases.getColumnAliases(),
						entityKey,
						existing
				);
				//we need to upgrade the lock mode to the mode requested
				context.getSession().getPersistenceContext().getEntry( existing ).setLockMode( requestedLockMode );
			}
		}
	}
