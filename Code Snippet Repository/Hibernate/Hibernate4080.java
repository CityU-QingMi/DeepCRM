	public String selectFragment(
			Joinable rhs,
			String rhsAlias,
			String lhsAlias,
			String entitySuffix,
			String collectionSuffix,
			boolean includeCollectionColumns) {
		// we need to determine the best way to know that two joinables
		// represent a single many-to-many...
		if ( rhs != null && isManyToMany() && !rhs.isCollection() ) {
			AssociationType elementType = ( (AssociationType) getElementType() );
			if ( rhs.equals( elementType.getAssociatedJoinable( getFactory() ) ) ) {
				return manyToManySelectFragment( rhs, rhsAlias, lhsAlias, collectionSuffix );
			}
		}
		return includeCollectionColumns ? selectFragment( lhsAlias, collectionSuffix ) : "";
	}
