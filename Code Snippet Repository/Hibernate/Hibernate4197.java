	@Override
	public Class<? extends EntityPersister> getEntityPersisterClass(PersistentClass metadata) {
		// todo : make sure this is based on an attribute kept on the metamodel in the new code, not the concrete PersistentClass impl found!
		if ( RootClass.class.isInstance( metadata ) ) {
			if ( metadata.hasSubclasses() ) {
				//If the class has children, we need to find of which kind
				metadata = (PersistentClass) metadata.getDirectSubclasses().next();
			}
			else {
				return singleTableEntityPersister();
			}
		}
		if ( JoinedSubclass.class.isInstance( metadata ) ) {
			return joinedSubclassEntityPersister();
		}
		else if ( UnionSubclass.class.isInstance( metadata ) ) {
			return unionSubclassEntityPersister();
		}
		else if ( SingleTableSubclass.class.isInstance( metadata ) ) {
			return singleTableEntityPersister();
		}
		else {
			throw new UnknownPersisterException(
					"Could not determine persister implementation for entity [" + metadata.getEntityName() + "]"
			);
		}
	}
