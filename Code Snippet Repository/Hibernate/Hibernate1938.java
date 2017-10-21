	public Map.Entry<Object, EntityEntry>[] reentrantSafeEntityEntries() {
		if ( dirty ) {
			reentrantSafeEntries = new EntityEntryCrossRefImpl[count];
			int i = 0;
			ManagedEntity managedEntity = head;
			while ( managedEntity != null ) {
				reentrantSafeEntries[i++] = new EntityEntryCrossRefImpl(
						managedEntity.$$_hibernate_getEntityInstance(),
						managedEntity.$$_hibernate_getEntityEntry()
				);
				managedEntity = managedEntity.$$_hibernate_getNextManagedEntity();
			}
			dirty = false;
		}
		return reentrantSafeEntries;
	}
