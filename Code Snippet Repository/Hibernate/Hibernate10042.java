	@Override
	@SuppressWarnings({""})
	protected void addToCollection(Object[] collection, Object collectionRow) {
		final Object elementData = ( (List) collectionRow ).get( elementComponentData.getComponentIndex() );
		final Object element = elementComponentData.getComponentMapper().mapToObjectFromFullMap(
				entityInstantiator,
				(Map<String, Object>) elementData, null, revision
		);

		final Object indexData = ( (List) collectionRow ).get( indexComponentData.getComponentIndex() );
		final Object indexObj = indexComponentData.getComponentMapper().mapToObjectFromFullMap(
				entityInstantiator,
				(Map<String, Object>) indexData, element, revision
		);
		final int index = ( (Number) indexObj ).intValue();

		collection[index] = element;
	}
