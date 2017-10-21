	private void dirty(PersistentCollection collection) throws HibernateException {

		boolean forceDirty = collection.wasInitialized() &&
				!collection.isDirty() && //optimization
				getLoadedPersister() != null &&
				getLoadedPersister().isMutable() && //optimization
				( collection.isDirectlyAccessible() || getLoadedPersister().getElementType().isMutable() ) && //optimization
				!collection.equalsSnapshot( getLoadedPersister() );

		if ( forceDirty ) {
			collection.dirty();
		}

	}
