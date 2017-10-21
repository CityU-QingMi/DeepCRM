	@Override
	protected void applyRootReturnSelectFragments(SelectStatementBuilder selectStatementBuilder) {
		selectStatementBuilder.appendSelectClauseFragment(
			getQueryableCollection().selectFragment(
					getCollectionReferenceAliases().getCollectionTableAlias(),
					getCollectionReferenceAliases().getCollectionColumnAliases().getSuffix()
			)
		);
		if ( getQueryableCollection().isManyToMany() ) {
			final OuterJoinLoadable elementPersister = (OuterJoinLoadable) getQueryableCollection().getElementPersister();
			selectStatementBuilder.appendSelectClauseFragment(
					elementPersister.selectFragment(
							getCollectionReferenceAliases().getElementTableAlias(),
							getCollectionReferenceAliases().getEntityElementAliases().getColumnAliases().getSuffix()
					)
			);
		}
		super.applyRootReturnSelectFragments( selectStatementBuilder );
	}
