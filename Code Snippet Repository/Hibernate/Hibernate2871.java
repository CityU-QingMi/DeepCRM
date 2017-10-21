	@Override
	protected IdsClauseBuilder newIdsClauseBuilder(List<Object[]> ids) {
		return new InlineIdsSubSelectValuesListBuilder(
				dialect(),
				getTargetedQueryable().getIdentifierType(),
				factory().getTypeResolver(),
				getTargetedQueryable().getIdentifierColumnNames(),
				ids
		);
	}
