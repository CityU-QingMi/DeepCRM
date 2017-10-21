	private void determineKeySelectExpressions(QueryableCollection collectionPersister, List selections) {
		AliasGenerator aliasGenerator = new LocalAliasGenerator( 0 );
		appendSelectExpressions( collectionPersister.getIndexColumnNames(), selections, aliasGenerator );
		Type keyType = collectionPersister.getIndexType();
		if ( keyType.isEntityType() ) {
			MapKeyEntityFromElement mapKeyEntityFromElement = findOrAddMapKeyEntityFromElement( collectionPersister );
			Queryable keyEntityPersister = mapKeyEntityFromElement.getQueryable();
			SelectFragment fragment = keyEntityPersister.propertySelectFragmentFragment(
					mapKeyEntityFromElement.getTableAlias(),
					null,
					false
			);
			appendSelectExpressions( fragment, selections, aliasGenerator );
		}
	}
