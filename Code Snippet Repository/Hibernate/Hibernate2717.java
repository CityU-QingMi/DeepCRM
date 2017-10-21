	private void determineValueSelectExpressions(QueryableCollection collectionPersister, List selections) {
		AliasGenerator aliasGenerator = new LocalAliasGenerator( 1 );
		appendSelectExpressions( collectionPersister.getElementColumnNames(), selections, aliasGenerator );
		Type valueType = collectionPersister.getElementType();
		if ( valueType.isAssociationType() ) {
			EntityType valueEntityType = (EntityType) valueType;
			Queryable valueEntityPersister = (Queryable) sfi().getEntityPersister(
					valueEntityType.getAssociatedEntityName( sfi() )
			);
			SelectFragment fragment = valueEntityPersister.propertySelectFragmentFragment(
					elementTableAlias(),
					null,
					false
			);
			appendSelectExpressions( fragment, selections, aliasGenerator );
		}
	}
