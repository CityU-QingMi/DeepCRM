	protected void initLockers() {
		lockers.put( LockMode.READ, generateLocker( LockMode.READ ) );
		lockers.put( LockMode.UPGRADE, generateLocker( LockMode.UPGRADE ) );
		lockers.put( LockMode.UPGRADE_NOWAIT, generateLocker( LockMode.UPGRADE_NOWAIT ) );
		lockers.put( LockMode.UPGRADE_SKIPLOCKED, generateLocker( LockMode.UPGRADE_SKIPLOCKED ) );
		lockers.put( LockMode.FORCE, generateLocker( LockMode.FORCE ) );
		lockers.put( LockMode.PESSIMISTIC_READ, generateLocker( LockMode.PESSIMISTIC_READ ) );
		lockers.put( LockMode.PESSIMISTIC_WRITE, generateLocker( LockMode.PESSIMISTIC_WRITE ) );
		lockers.put( LockMode.PESSIMISTIC_FORCE_INCREMENT, generateLocker( LockMode.PESSIMISTIC_FORCE_INCREMENT ) );
		lockers.put( LockMode.OPTIMISTIC, generateLocker( LockMode.OPTIMISTIC ) );
		lockers.put( LockMode.OPTIMISTIC_FORCE_INCREMENT, generateLocker( LockMode.OPTIMISTIC_FORCE_INCREMENT ) );
	}
