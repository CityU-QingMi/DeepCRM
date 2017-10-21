	private void initText() {
		String[] cols = getColumns();
		String text = StringHelper.join( ", ", cols );
		boolean countDistinct = getWalker().isInCountDistinct()
				&& getWalker().getSessionFactoryHelper().getFactory().getDialect().requiresParensForTupleDistinctCounts();
		if ( cols.length > 1 &&
				( getWalker().isComparativeExpressionClause() || countDistinct ) ) {
			text = "(" + text + ")";
		}
		setText( text );
	}
