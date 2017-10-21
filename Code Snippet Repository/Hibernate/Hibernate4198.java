	public static Iterable<AttributeDefinition> getCompositeCollectionElementSubAttributes(
			CompositeCollectionElementDefinition compositionElementDefinition) {
		final QueryableCollection collectionPersister =
				(QueryableCollection) compositionElementDefinition.getCollectionDefinition().getCollectionPersister();
		return getSingularSubAttributes(
				compositionElementDefinition.getSource(),
				(OuterJoinLoadable) collectionPersister.getOwnerEntityPersister(),
				(CompositeType) collectionPersister.getElementType(),
				collectionPersister.getTableName(),
				collectionPersister.getElementColumnNames()
		);
	}
