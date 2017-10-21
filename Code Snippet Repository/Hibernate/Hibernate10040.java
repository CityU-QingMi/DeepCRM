	@Override
	public T initialize() {
		final List<?> collectionContent = queryGenerator.getQuery( versionsReader, primaryKey, revision, removed ).list();

		final T collection = initializeCollection( collectionContent.size() );

		for ( Object collectionRow : collectionContent ) {
			addToCollection( collection, collectionRow );
		}

		return collection;
	}
