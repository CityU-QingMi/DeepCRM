	private boolean hasDirtyCollections(FlushEntityEvent event, EntityPersister persister, Status status) {
		if ( isCollectionDirtyCheckNecessary( persister, status ) ) {
			DirtyCollectionSearchVisitor visitor = new DirtyCollectionSearchVisitor(
					event.getSession(),
					persister.getPropertyVersionability()
			);
			visitor.processEntityPropertyValues( event.getPropertyValues(), persister.getPropertyTypes() );
			boolean hasDirtyCollections = visitor.wasDirtyCollectionFound();
			event.setHasDirtyCollection( hasDirtyCollections );
			return hasDirtyCollections;
		}
		else {
			return false;
		}
	}
