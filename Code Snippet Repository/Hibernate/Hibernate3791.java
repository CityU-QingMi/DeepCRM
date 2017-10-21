	@Override
	protected void applyRootReturnOrderByFragments(SelectStatementBuilder selectStatementBuilder) {
		final String manyToManyOrdering = getQueryableCollection().getManyToManyOrderByString(
				getCollectionReferenceAliases().getElementTableAlias()
		);
		if ( StringHelper.isNotEmpty( manyToManyOrdering ) ) {
			selectStatementBuilder.appendOrderByFragment( manyToManyOrdering );
		}
		super.applyRootReturnOrderByFragments( selectStatementBuilder );
	}
