	public ExpandingEntityQuerySpace makeEntityQuerySpace(
			ExpandingQuerySpace lhsQuerySpace,
			AssociationAttributeDefinition attribute,
			String querySpaceUid,
			FetchStrategy fetchStrategy) {
		final EntityType fetchedType = (EntityType) attribute.getType();
		final EntityPersister fetchedPersister = attribute.toEntityDefinition().getEntityPersister();

		if ( fetchedPersister == null ) {
			throw new WalkingException(
					String.format(
							"Unable to locate EntityPersister [%s] for fetch [%s]",
							fetchedType.getAssociatedEntityName(),
							attribute.getName()
					)
			);
		}
		// TODO: Queryable.isMultiTable() may be more broad than it needs to be...
		final boolean isMultiTable = Queryable.class.cast( fetchedPersister ).isMultiTable();
		final boolean required = lhsQuerySpace.canJoinsBeRequired() && !isMultiTable && !attribute.isNullable();

		return makeEntityQuerySpace(
				lhsQuerySpace,
				fetchedPersister,
				attribute.getName(),
				(EntityType) attribute.getType(),
				querySpaceUid,
				required,
				shouldIncludeJoin( fetchStrategy )
		);
	}
