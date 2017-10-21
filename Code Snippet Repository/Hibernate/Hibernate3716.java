	private CollectionFetchableIndex buildIndexGraph() {
		final CollectionPersister persister = collectionQuerySpace.getCollectionPersister();
		if ( persister.hasIndex() ) {
			final Type type = persister.getIndexType();
			if ( type.isAssociationType() ) {
				if ( type.isEntityType() ) {
					final EntityPersister indexPersister = persister.getFactory().getEntityPersister(
							( (EntityType) type ).getAssociatedEntityName()
					);

					final ExpandingEntityQuerySpace entityQuerySpace = QuerySpaceHelper.INSTANCE.makeEntityQuerySpace(
							collectionQuerySpace,
							indexPersister,
							CollectionPropertyNames.COLLECTION_INDICES,
							(EntityType) persister.getIndexType(),
							collectionQuerySpace.getExpandingQuerySpaces().generateImplicitUid(),
							collectionQuerySpace.canJoinsBeRequired(),
							allowIndexJoin
					);
					return new CollectionFetchableIndexEntityGraph( this, entityQuerySpace );
				}
				else if ( type.isAnyType() ) {
					return new CollectionFetchableIndexAnyGraph( this );
				}
			}
			else if ( type.isComponentType() ) {
				final ExpandingCompositeQuerySpace compositeQuerySpace = QuerySpaceHelper.INSTANCE.makeCompositeQuerySpace(
						collectionQuerySpace,
						new CompositePropertyMapping(
								(CompositeType) persister.getIndexType(),
								(PropertyMapping) persister,
								""
						),
						CollectionPropertyNames.COLLECTION_INDICES,
						(CompositeType) persister.getIndexType(),
						collectionQuerySpace.getExpandingQuerySpaces().generateImplicitUid(),
						collectionQuerySpace.canJoinsBeRequired(),
						allowIndexJoin
				);
				return new CollectionFetchableIndexCompositeGraph( this, compositeQuerySpace );
			}
		}

		return null;
	}
