	protected void applyRootReturnSelectFragments(SelectStatementBuilder selectStatementBuilder) {
		final OuterJoinLoadable outerJoinLoadable = (OuterJoinLoadable) getRootEntityReturn().getEntityPersister();
		selectStatementBuilder.appendSelectClauseFragment(
				outerJoinLoadable.selectFragment(
						entityReferenceAliases.getTableAlias(),
						entityReferenceAliases.getColumnAliases().getSuffix()

				)
		);
	}
