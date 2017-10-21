	private void processCollectionReturn(NativeSQLQueryCollectionReturn collectionReturn) {
		// we are initializing an owned collection
		//collectionOwners.add( new Integer(-1) );
//		collectionOwnerAliases.add( null );
		String role = collectionReturn.getOwnerEntityName() + '.' + collectionReturn.getOwnerProperty();
		addCollection(
				role,
				collectionReturn.getAlias(),
				collectionReturn.getPropertyResultsMap()
		);
	}
