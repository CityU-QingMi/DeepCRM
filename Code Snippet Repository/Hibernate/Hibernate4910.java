	private <T> T[] index(Class<? extends T[]> clazz, T[] objects) {
		T[] objectsIndexed = objects;
		if ( objects != null &&
				includeInTransformIndex != null &&
				objects.length != tupleSubsetLength ) {
			objectsIndexed = clazz.cast( Array.newInstance( clazz.getComponentType(), tupleSubsetLength ) );
			for ( int i = 0 ; i < tupleSubsetLength; i++ ) {
				objectsIndexed[ i ] = objects[ includeInTransformIndex[ i ] ];
			}
		}
		return objectsIndexed;
	}
