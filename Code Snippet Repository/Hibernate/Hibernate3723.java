	@Override
	public BidirectionalEntityReference buildBidirectionalEntityReference(
			AssociationAttributeDefinition attributeDefinition,
			FetchStrategy fetchStrategy,
			EntityReference targetEntityReference) {
		final EntityType fetchedType = (EntityType) attributeDefinition.getType();
		final EntityPersister fetchedPersister = attributeDefinition.toEntityDefinition().getEntityPersister();

		if ( fetchedPersister == null ) {
			throw new WalkingException(
					String.format(
							"Unable to locate EntityPersister [%s] for bidirectional entity reference [%s]",
							fetchedType.getAssociatedEntityName(),
							attributeDefinition.getName()
					)
			);
		}

		final BidirectionalEntityReference bidirectionalEntityReference =
				new BidirectionalEntityReferenceImpl( this, attributeDefinition, targetEntityReference );
		addBidirectionalEntityReference( bidirectionalEntityReference );
		return bidirectionalEntityReference;
	}
