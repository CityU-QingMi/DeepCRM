	public ExpandingCompositeQuerySpace makeCompositeQuerySpace(
			ExpandingQuerySpace lhsQuerySpace,
			AttributeDefinition attributeDefinition,
			String querySpaceUid,
			boolean shouldIncludeJoin) {
		final boolean required = lhsQuerySpace.canJoinsBeRequired() && !attributeDefinition.isNullable();
		return makeCompositeQuerySpace(
				lhsQuerySpace,
				new CompositePropertyMapping(
						(CompositeType) attributeDefinition.getType(),
						lhsQuerySpace.getPropertyMapping(),
						attributeDefinition.getName()
				),
				attributeDefinition.getName(),
				(CompositeType) attributeDefinition.getType(),
				querySpaceUid,
				required,
				shouldIncludeJoin
		);
	}
