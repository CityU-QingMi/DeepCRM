	public ExpandingCollectionQuerySpace makeCollectionQuerySpace(
			ExpandingQuerySpace lhsQuerySpace,
			AssociationAttributeDefinition attributeDefinition,
			String querySpaceUid,
			FetchStrategy fetchStrategy) {

		final CollectionType fetchedType = (CollectionType) attributeDefinition.getType();
		final CollectionPersister fetchedPersister = attributeDefinition.toCollectionDefinition()
				.getCollectionPersister();

		if ( fetchedPersister == null ) {
			throw new WalkingException(
					String.format(
							"Unable to locate CollectionPersister [%s] for fetch [%s]",
							fetchedType.getRole(),
							attributeDefinition.getName()
					)
			);
		}

		final boolean required = lhsQuerySpace.canJoinsBeRequired() && !attributeDefinition.isNullable();

		final ExpandingCollectionQuerySpace rhs = lhsQuerySpace.getExpandingQuerySpaces().makeCollectionQuerySpace(
				querySpaceUid,
				fetchedPersister,
				required
		);

		if ( shouldIncludeJoin( fetchStrategy ) ) {
			final JoinDefinedByMetadata join = JoinHelper.INSTANCE.createCollectionJoin(
					lhsQuerySpace,
					attributeDefinition.getName(),
					rhs,
					required,
					(CollectionType) attributeDefinition.getType(),
					fetchedPersister.getFactory()
			);
			lhsQuerySpace.addJoin( join );
		}

		return rhs;
	}
