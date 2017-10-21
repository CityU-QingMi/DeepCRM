	public ExpandingEntityQuerySpace makeEntityQuerySpace(
			ExpandingQuerySpace lhsQuerySpace,
			EntityPersister fetchedPersister,
			String attributeName,
			EntityType attributeType,
			String querySpaceUid,
			boolean required,
			boolean shouldIncludeJoin) {

		final ExpandingEntityQuerySpace rhs = lhsQuerySpace.getExpandingQuerySpaces().makeEntityQuerySpace(
				querySpaceUid,
				fetchedPersister,
				required
		);

		if ( shouldIncludeJoin ) {
			final JoinDefinedByMetadata join = JoinHelper.INSTANCE.createEntityJoin(
					lhsQuerySpace,
					attributeName,
					rhs,
					required,
					attributeType,
					fetchedPersister.getFactory()
			);
			lhsQuerySpace.addJoin( join );
		}

		return rhs;
	}
