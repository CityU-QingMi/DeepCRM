	private void dereferenceCollection(String propertyName, String role, QueryTranslatorImpl q) throws QueryException {
		collectionRole = role;
		QueryableCollection collPersister = q.getCollectionPersister( role );
		String name = q.createNameForCollection( role );
		addJoin( name, collPersister.getCollectionType() );
		//if ( collPersister.hasWhere() ) join.addCondition( collPersister.getSQLWhereString(name) );
		collectionName = name;
		collectionOwnerName = currentName;
		currentName = name;
		currentProperty = propertyName;
		componentPath.setLength( 0 );
		currentPropertyMapping = new CollectionPropertyMapping( collPersister );
	}
