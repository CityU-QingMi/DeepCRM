	@Override
	@SuppressWarnings({""})
	protected void addToCollection(T collection, Object collectionRow) {
		// collectionRow will be the actual object if retrieved from audit relation or middle table
		// otherwise it will be a List
		Object elementData = collectionRow;
		if ( collectionRow instanceof java.util.List ) {
			elementData = ( (List) collectionRow ).get( elementComponentData.getComponentIndex() );
		}

		// If the target entity is not audited, the elements may be the entities already, so we have to check
		// if they are maps or not.
		Object element;
		if ( elementData instanceof Map ) {
			element = elementComponentData.getComponentMapper().mapToObjectFromFullMap(
					entityInstantiator,
					(Map<String, Object>) elementData, null, revision
			);
		}
		else {
			element = elementData;
		}
		collection.add( element );
	}
