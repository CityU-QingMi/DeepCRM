	private <T> T[] unindex(Class<? extends T[]> clazz, T[] objects) {
		T[] objectsUnindexed = objects;
		if ( objects != null &&
				includeInTransformIndex != null &&
				objects.length != tupleLength ) {
			objectsUnindexed = clazz.cast( Array.newInstance( clazz.getComponentType(), tupleLength ) );
			for ( int i = 0 ; i < tupleSubsetLength; i++ ) {
				objectsUnindexed[ includeInTransformIndex[ i ] ] = objects[ i ];
			}
		}
		return objectsUnindexed;
	}
