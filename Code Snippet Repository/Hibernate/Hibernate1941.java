	public void serialize(ObjectOutputStream oos) throws IOException {
		log.tracef( "Starting serialization of [%s] EntityEntry entries", count );
		oos.writeInt( count );
		if ( count == 0 ) {
			return;
		}

		ManagedEntity managedEntity = head;
		while ( managedEntity != null ) {
			// so we know whether or not to build a ManagedEntityImpl on deserialize
			oos.writeBoolean( managedEntity == managedEntity.$$_hibernate_getEntityInstance() );
			oos.writeObject( managedEntity.$$_hibernate_getEntityInstance() );
			// we need to know which implementation of EntityEntry is being serialized
			oos.writeInt( managedEntity.$$_hibernate_getEntityEntry().getClass().getName().length() );
			oos.writeChars( managedEntity.$$_hibernate_getEntityEntry().getClass().getName() );
			managedEntity.$$_hibernate_getEntityEntry().serialize( oos );

			managedEntity = managedEntity.$$_hibernate_getNextManagedEntity();
		}
	}
