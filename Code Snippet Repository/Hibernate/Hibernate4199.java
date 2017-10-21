	public static Iterable<AttributeDefinition> getCompositeCollectionIndexSubAttributes(CompositeCollectionElementDefinition compositionElementDefinition){
		final QueryableCollection collectionPersister =
				(QueryableCollection) compositionElementDefinition.getCollectionDefinition().getCollectionPersister();
		return getSingularSubAttributes(
				compositionElementDefinition.getSource(),
				(OuterJoinLoadable) collectionPersister.getOwnerEntityPersister(),
				(CompositeType) collectionPersister.getIndexType(),
				collectionPersister.getTableName(),
				collectionPersister.toColumns( "index" )
		);
	}
