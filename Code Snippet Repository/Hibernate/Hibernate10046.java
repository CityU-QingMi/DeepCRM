	@Override
	@SuppressWarnings({""})
	protected List initializeCollection(int size) {
		// Creating a list of the given capacity with all elements null initially. This ensures that we can then
		// fill the elements safely using the <code>List.set</code> method.
		final List list = new ArrayList( size );
		for ( int i = 0; i < size; i++ ) {
			list.add( null );
		}
		return list;
	}
