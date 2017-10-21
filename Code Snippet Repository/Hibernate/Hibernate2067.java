	public static boolean isVersionIncrementRequired(
			final int[] dirtyProperties,
			final boolean hasDirtyCollections,
			final boolean[] propertyVersionability) {
		if ( hasDirtyCollections ) {
			return true;
		}
		for ( int dirtyProperty : dirtyProperties ) {
			if ( propertyVersionability[dirtyProperty] ) {
				return true;
			}
		}
		return false;
	}
