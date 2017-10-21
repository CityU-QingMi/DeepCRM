	@Override
	public ExpandingCompositeQuerySpace makeCompositeIdentifierQuerySpace() {
		final String compositeQuerySpaceUid = getUid() + "-id";
		final ExpandingCompositeQuerySpace rhs = getExpandingQuerySpaces().makeCompositeQuerySpace(
				compositeQuerySpaceUid,
				new CompositePropertyMapping(
						(CompositeType) getEntityPersister().getIdentifierType(),
						(PropertyMapping) getEntityPersister(),
						getEntityPersister().getIdentifierPropertyName()
				),
				canJoinsBeRequired()
		);
		final JoinDefinedByMetadata join = JoinHelper.INSTANCE.createCompositeJoin(
				this,
				EntityPersister.ENTITY_ID,
				rhs,
				canJoinsBeRequired(),
				(CompositeType) persister.getIdentifierType()
		);
		internalGetJoins().add( join );

		return rhs;
	}
