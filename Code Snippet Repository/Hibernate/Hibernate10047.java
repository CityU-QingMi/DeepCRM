	@Override
	@SuppressWarnings({""})
	protected void addToCollection(List collection, Object collectionRow) {
		// collectionRow will be the actual object if retrieved from audit relation or middle table
		// otherwise it will be a List
		Object elementData = collectionRow;
		Object indexData = collectionRow;
		if ( collectionRow instanceof java.util.List ) {
			elementData = ( (List) collectionRow ).get( elementComponentData.getComponentIndex() );
			indexData = ( (List) collectionRow ).get( indexComponentData.getComponentIndex() );
		}
		final Object element = elementData instanceof Map
				? elementComponentData.getComponentMapper().mapToObjectFromFullMap(
						entityInstantiator,
						(Map<String, Object>) elementData, null, revision
				)
				: elementData;

		final Object indexObj = indexComponentData.getComponentMapper().mapToObjectFromFullMap(
				entityInstantiator,
				(Map<String, Object>) indexData, element, revision
		);
		final int index = ( (Number) indexObj ).intValue();

		collection.set( index, element );
	}
