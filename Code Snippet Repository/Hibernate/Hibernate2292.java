	public static Iterator getLoadedElementsIterator(
			SharedSessionContractImplementor session,
			CollectionType collectionType,
			Object collection) {
		if ( collectionIsInitialized( collection ) ) {
			// handles arrays and newly instantiated collections
			return collectionType.getElementsIterator( collection, session );
		}
		else {
			// does not handle arrays (thats ok, cos they can't be lazy)
			// or newly instantiated collections, so we can do the cast
			return ((PersistentCollection) collection).queuedAdditionIterator();
		}
	}
