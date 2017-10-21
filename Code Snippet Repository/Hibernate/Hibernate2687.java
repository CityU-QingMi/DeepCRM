	public String renderValueCollectionSelectFragment(int size, int k) {
		if ( queryableCollection == null ) {
			return "";
		}
		else {
			if ( collectionSuffix == null ) {
				collectionSuffix = generateSuffix( size, k );
			}
			String fragment = queryableCollection.selectFragment( getTableAlias(), collectionSuffix );
			return trimLeadingCommaAndSpaces( fragment );
		}
	}
