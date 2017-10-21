	@Override
	protected IdsClauseBuilder newIdsClauseBuilder(List<Object[]> ids) {
		return new InlineIdsOrClauseBuilder(
				dialect(),
				getTargetedQueryable().getIdentifierType(),
				factory().getTypeResolver(),
				getTargetedQueryable().getIdentifierColumnNames(),
				ids
		);
	}
