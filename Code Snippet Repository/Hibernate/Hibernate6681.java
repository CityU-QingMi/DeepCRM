	private boolean isDefaultColumnPresent(String collectionOwner, String propertyName, String suffix) {
		final Collection collection = metadata().getCollectionBinding( collectionOwner + "." + propertyName );
		final Iterator columnIterator = collection.getCollectionTable().getColumnIterator();
		boolean hasDefault = false;
		while ( columnIterator.hasNext() ) {
			Column column = (Column) columnIterator.next();
			if ( (propertyName + suffix).equals( column.getName() ) ) hasDefault = true;
		}
		return hasDefault;
	}
