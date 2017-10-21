	@Override
	public void overwriteLoadedStateCollectionValue(String propertyName, PersistentCollection collection) {
		// nothing to do if status is READ_ONLY
		if ( getStatus() != Status.READ_ONLY ) {
			assert propertyName != null;
			assert loadedState != null;

			final int propertyIndex = ( (UniqueKeyLoadable) persister ).getPropertyIndex( propertyName );
			loadedState[propertyIndex] = collection;
		}
	}
