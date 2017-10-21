	private void initAliases(SelectExpression[] selectExpressions) {
		if ( aggregatedSelectExpression == null ) {
			aliases = new String[selectExpressions.length];
			for ( int i = 0; i < selectExpressions.length; i++ ) {
				aliases[i] = selectExpressions[i].getAlias();
			}
		}
		else {
			aliases = aggregatedSelectExpression.getAggregatedAliases();
		}
	}
