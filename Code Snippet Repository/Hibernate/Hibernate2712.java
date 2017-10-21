	@Override
	@SuppressWarnings("")
	protected Type resolveType(QueryableCollection collectionPersister) {
		final Type keyType = collectionPersister.getIndexType();
		final Type valueType = collectionPersister.getElementType();
		types.add( keyType );
		types.add( valueType );
		mapEntryBuilder = new MapEntryBuilder();

		// an entry (as an aggregated select expression) does not have a type...
		return null;
	}
