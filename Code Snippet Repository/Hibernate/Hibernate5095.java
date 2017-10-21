	protected EntityPersister getAssociatedEntityPersister(final SessionFactoryImplementor factory) {
		final EntityPersister persister = associatedEntityPersister;
		//The following branch implements a simple lazy-initialization, but rather than the canonical
		//form it returns the local variable to avoid a second volatile read: associatedEntityPersister
		//needs to be volatile as the initialization might happen by a different thread than the readers.
		if ( persister == null ) {
			associatedEntityPersister = factory.getMetamodel().entityPersister( getAssociatedEntityName() );
			return associatedEntityPersister;
		}
		else {
			return persister;
		}
	}
