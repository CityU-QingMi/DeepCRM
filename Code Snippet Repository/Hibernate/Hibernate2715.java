	@SuppressWarnings({"", ""})
	private void appendSelectExpressions(String[] columnNames, List selections, AliasGenerator aliasGenerator) {
		for ( int i = 0; i < columnNames.length; i++ ) {
			selections.add(
					new BasicSelectExpression(
							collectionTableAlias() + '.' + columnNames[i],
							aliasGenerator.generateAlias( columnNames[i] )
					)
			);
		}
	}
