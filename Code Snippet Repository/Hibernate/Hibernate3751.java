	public ExpandingCompositeQuerySpace makeCompositeQuerySpace(
			ExpandingQuerySpace lhsQuerySpace,
			CompositePropertyMapping compositePropertyMapping,
			String attributeName,
			CompositeType attributeType,
			String querySpaceUid,
			boolean required,
			boolean shouldIncludeJoin) {

		final ExpandingCompositeQuerySpace rhs = lhsQuerySpace.getExpandingQuerySpaces().makeCompositeQuerySpace(
				querySpaceUid,
				compositePropertyMapping,
				required
		);

		if ( shouldIncludeJoin ) {
			final JoinDefinedByMetadata join = JoinHelper.INSTANCE.createCompositeJoin(
					lhsQuerySpace,
					attributeName,
					rhs,
					required,
					attributeType
			);
			lhsQuerySpace.addJoin( join );
		}

		return rhs;
	}
