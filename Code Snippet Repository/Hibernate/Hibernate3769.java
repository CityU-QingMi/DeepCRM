	@Override
	protected  void applyRootReturnSelectFragments(SelectStatementBuilder selectStatementBuilder) {
		if ( getRootCollectionReturn().allowIndexJoin() && getQueryableCollection().getIndexType().isEntityType() ) {
			final EntityReference indexEntityReference = getRootCollectionReturn().getIndexGraph().resolveEntityReference();
			final EntityReferenceAliases indexEntityReferenceAliases = getAliasResolutionContext().resolveEntityReferenceAliases(
					indexEntityReference.getQuerySpaceUid()
			);
			selectStatementBuilder.appendSelectClauseFragment(
					( (OuterJoinLoadable) indexEntityReference.getEntityPersister() ).selectFragment(
							indexEntityReferenceAliases.getTableAlias(),
							indexEntityReferenceAliases.getColumnAliases().getSuffix()
					)
			);
		}
	}
