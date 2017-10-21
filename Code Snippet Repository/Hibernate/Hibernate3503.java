	private void instanceAlreadyLoaded(
			final ResultSet rs,
			final int i,
			final Loadable persister,
			final EntityKey key,
			final Object object,
			final LockMode requestedLockMode,
			final SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if ( !persister.isInstance( object ) ) {
			throw new WrongClassException(
					"loaded object was of wrong class " + object.getClass(),
					key.getIdentifier(),
					persister.getEntityName()
			);
		}

		if ( LockMode.NONE != requestedLockMode && upgradeLocks() ) { //no point doing this if NONE was requested
			final EntityEntry entry = session.getPersistenceContext().getEntry( object );
			if ( entry.getLockMode().lessThan( requestedLockMode ) ) {
				//we only check the version when _upgrading_ lock modes
				if ( persister.isVersioned() ) {
					checkVersion( i, persister, key.getIdentifier(), object, rs, session );
				}
				//we need to upgrade the lock mode to the mode requested
				entry.setLockMode( requestedLockMode );
			}
		}
	}
