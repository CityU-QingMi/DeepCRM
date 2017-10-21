	private CollectionFetchableElement buildElementGraph() {
		final CollectionPersister persister = collectionQuerySpace.getCollectionPersister();
		final Type type = persister.getElementType();
		if ( type.isAssociationType() ) {
			if ( type.isEntityType() ) {
				final EntityPersister elementPersister = persister.getFactory().getEntityPersister(
						( (EntityType) type ).getAssociatedEntityName()
				);
				final ExpandingEntityQuerySpace entityQuerySpace = QuerySpaceHelper.INSTANCE.makeEntityQuerySpace(
						collectionQuerySpace,
						elementPersister,
						CollectionPropertyNames.COLLECTION_ELEMENTS,
						(EntityType) persister.getElementType(),
						collectionQuerySpace.getExpandingQuerySpaces().generateImplicitUid(),
						collectionQuerySpace.canJoinsBeRequired(),
						allowElementJoin
				);
				return new CollectionFetchableElementEntityGraph( this, entityQuerySpace );
			}
			else if ( type.isAnyType() ) {
				return new CollectionFetchableElementAnyGraph( this );
			}
		}
		else if ( type.isComponentType() ) {
			final ExpandingCompositeQuerySpace compositeQuerySpace = QuerySpaceHelper.INSTANCE.makeCompositeQuerySpace(
					collectionQuerySpace,
					new CompositePropertyMapping(
							(CompositeType) persister.getElementType(),
							(PropertyMapping) persister,
							""
					),
					CollectionPropertyNames.COLLECTION_ELEMENTS,
					(CompositeType) persister.getElementType(),
					collectionQuerySpace.getExpandingQuerySpaces().generateImplicitUid(),
					collectionQuerySpace.canJoinsBeRequired(),
					allowElementJoin
			);
			return new CollectionFetchableElementCompositeGraph( this, compositeQuerySpace );
		}

		return null;
	}
