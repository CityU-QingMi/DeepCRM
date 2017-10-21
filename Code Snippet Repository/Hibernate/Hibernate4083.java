	public Type toType(String propertyName) throws QueryException {
		if ( propertyName.equals(CollectionPropertyNames.COLLECTION_ELEMENTS) ) {
			return memberPersister.getElementType();
		}
		else if ( propertyName.equals(CollectionPropertyNames.COLLECTION_INDICES) ) {
			if ( !memberPersister.hasIndex() ) {
				throw new QueryException("unindexed collection before indices()");
			}
			return memberPersister.getIndexType();
		}
		else if ( propertyName.equals(CollectionPropertyNames.COLLECTION_SIZE) ) {
			return StandardBasicTypes.INTEGER;
		}
		else if ( propertyName.equals(CollectionPropertyNames.COLLECTION_MAX_INDEX) ) {
			return memberPersister.getIndexType();
		}
		else if ( propertyName.equals(CollectionPropertyNames.COLLECTION_MIN_INDEX) ) {
			return memberPersister.getIndexType();
		}
		else if ( propertyName.equals(CollectionPropertyNames.COLLECTION_MAX_ELEMENT) ) {
			return memberPersister.getElementType();
		}
		else if ( propertyName.equals(CollectionPropertyNames.COLLECTION_MIN_ELEMENT) ) {
			return memberPersister.getElementType();
		}
		else {
			//return memberPersister.getPropertyType(propertyName);
			throw new QueryException("illegal syntax near collection: " + propertyName);
		}
	}
