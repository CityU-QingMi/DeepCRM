	@SuppressWarnings({"", ""})
	private void appendSelectExpressions(SelectFragment fragment, List selections, AliasGenerator aliasGenerator) {
		Iterator itr = fragment.getColumns().iterator();
		while ( itr.hasNext() ) {
			final String column = (String) itr.next();
			selections.add(
					new BasicSelectExpression( column, aliasGenerator.generateAlias( column ) )
			);
		}
	}
