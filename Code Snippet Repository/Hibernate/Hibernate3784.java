	private EntityReferenceAliases createCollectionElementAliases(
			CollectionPersister collectionPersister,
			String tableAlias,
			String elementQuerySpaceUid) {

		if ( !collectionPersister.getElementType().isEntityType() ) {
			return null;
		}
		else {
			final EntityType entityElementType = (EntityType) collectionPersister.getElementType();
			return generateEntityReferenceAliases(
					elementQuerySpaceUid,
					tableAlias,
					(EntityPersister) entityElementType.getAssociatedJoinable( sessionFactory() )
			);
		}
	}
