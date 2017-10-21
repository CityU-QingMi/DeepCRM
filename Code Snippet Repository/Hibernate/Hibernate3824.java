	protected Object findCollectionOwner(
			Serializable collectionRowKey,
			ResultSet resultSet,
			ResultSetProcessingContextImpl context) {
		final Object collectionOwner = context.getSession().getPersistenceContext().getCollectionOwner(
				collectionRowKey,
				collectionReference.getCollectionPersister()
		);
		// todo : try org.hibernate.loader.plan.exec.process.spi.ResultSetProcessingContext.getOwnerProcessingState() ??
		//			-- specifically to return its ResultSetProcessingContext.EntityReferenceProcessingState#getEntityInstance()
		if ( collectionOwner == null ) {
			//TODO: This is assertion is disabled because there is a bug that means the
			//	  original owner of a transient, uninitialized collection is not known
			//	  if the collection is re-referenced by a different object associated
			//	  with the current Session
			//throw new AssertionFailure("bug loading unowned collection");
		}
		return collectionOwner;
	}
