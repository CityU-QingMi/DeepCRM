	@Override
	public Object getLoadedValue(String propertyName) {
		if ( loadedState == null || propertyName == null ) {
			return null;
		}
		else {
			final int propertyIndex = ( (UniqueKeyLoadable) persister ).getPropertyIndex( propertyName );
			return loadedState[propertyIndex];
		}
	}
