	@Override
	protected void applyRootReturnSelectFragments(SelectStatementBuilder selectStatementBuilder) {

		selectStatementBuilder.appendSelectClauseFragment(
				getQueryableCollection().selectFragment(
						null,
						null,
						//getCollectionReferenceAliases().getCollectionTableAlias(),
						getElementEntityReferenceAliases().getTableAlias(),
						getElementEntityReferenceAliases().getColumnAliases().getSuffix(),
						getCollectionReferenceAliases().getCollectionColumnAliases().getSuffix(),
						true
				)
		);
		super.applyRootReturnSelectFragments( selectStatementBuilder );
	}
