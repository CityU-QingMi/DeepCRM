	public void end(QueryTranslatorImpl q) throws QueryException {
		ignoreInitialJoin = false;

		Type propertyType = getPropertyType();
		if ( propertyType != null && propertyType.isCollectionType() ) {
			collectionRole = ( ( CollectionType ) propertyType ).getRole();
			collectionName = q.createNameForCollection( collectionRole );
			prepareForIndex( q );
		}
		else {
			columns = currentColumns();
			setType();
		}

		//important!!
		continuation = false;

	}
