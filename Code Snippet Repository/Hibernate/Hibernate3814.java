	@Override
	protected void applyRootReturnTableFragments(SelectStatementBuilder selectStatementBuilder) {
		final OuterJoinLoadable elementOuterJoinLoadable =
				(OuterJoinLoadable) getElementEntityReference().getEntityPersister();
		//final String tableAlias = getCollectionReferenceAliases().getCollectionTableAlias();
		final String tableAlias = getElementEntityReferenceAliases().getTableAlias();
		final String fragment =
				elementOuterJoinLoadable.fromTableFragment( tableAlias ) +
						elementOuterJoinLoadable.fromJoinFragment( tableAlias, true, true);
		selectStatementBuilder.appendFromClauseFragment( fragment );
	}
