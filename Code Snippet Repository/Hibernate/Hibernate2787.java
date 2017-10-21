	public void end(QueryTranslatorImpl q) throws QueryException {
		if ( !isCollectionValued() ) {
			Type type = getPropertyType();
			if ( type.isEntityType() ) {
				// "finish off" the join
				token( ".", q );
				token( null, q );
			}
			else if ( type.isCollectionType() ) {
				// default to element set if no elements() specified
				token( ".", q );
				token( CollectionPropertyNames.COLLECTION_ELEMENTS, q );
			}
		}
		super.end( q );
	}
