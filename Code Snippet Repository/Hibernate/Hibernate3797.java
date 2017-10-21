	protected void applyRootReturnWhereJoinRestrictions(SelectStatementBuilder selectStatementBuilder) {
		final Joinable joinable = (OuterJoinLoadable) getRootEntityReturn().getEntityPersister();
		selectStatementBuilder.appendRestrictions(
				joinable.whereJoinFragment(
						entityReferenceAliases.getTableAlias(),
						true,
						true
				)
		);
	}
