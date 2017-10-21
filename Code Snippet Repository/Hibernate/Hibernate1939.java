	public void clear() {
		dirty = true;

		ManagedEntity node = head;
		while ( node != null ) {
			final ManagedEntity nextNode = node.$$_hibernate_getNextManagedEntity();

			node.$$_hibernate_setEntityEntry( null );

			node.$$_hibernate_setPreviousManagedEntity( null );
			node.$$_hibernate_setNextManagedEntity( null );

			node = nextNode;
		}

		if ( immutableManagedEntityXref != null ) {
			immutableManagedEntityXref.clear();
		}

		if ( nonEnhancedEntityXref != null ) {
			nonEnhancedEntityXref.clear();
		}

		head = null;
		tail = null;
		count = 0;

		reentrantSafeEntries = null;
	}
