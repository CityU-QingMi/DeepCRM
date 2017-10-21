	private void isCollectionColumnPresent(String collectionOwner, String propertyName, String columnName) {
		final Collection collection = metadata().getCollectionBinding( collectionOwner + "." + propertyName );
		final Iterator columnIterator = collection.getCollectionTable().getColumnIterator();
		boolean hasDefault = false;
		while ( columnIterator.hasNext() ) {
			Column column = (Column) columnIterator.next();
			if ( columnName.equals( column.getName() ) ) hasDefault = true;
		}
		assertTrue( "Could not find " + columnName, hasDefault );
	}
