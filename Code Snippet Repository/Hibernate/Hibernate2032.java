	private Object getIndexInParent(
			String property,
			Object childEntity,
			EntityPersister persister,
			CollectionPersister collectionPersister,
			Object potentialParent){
		final Object collection = persister.getPropertyValue( potentialParent, property );
		if ( collection != null && Hibernate.isInitialized( collection ) ) {
			return collectionPersister.getCollectionType().indexOf( collection, childEntity );
		}
		else {
			return null;
		}
	}
