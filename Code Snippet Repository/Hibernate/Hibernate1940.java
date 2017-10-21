	public void downgradeLocks() {
		if ( head == null ) {
			return;
		}

		ManagedEntity node = head;
		while ( node != null ) {
			node.$$_hibernate_getEntityEntry().setLockMode( LockMode.NONE );

			node = node.$$_hibernate_getNextManagedEntity();
		}
	}
