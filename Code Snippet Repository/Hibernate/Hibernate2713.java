	@Override
	protected String[] resolveColumns(QueryableCollection collectionPersister) {
		List selections = new ArrayList();
		determineKeySelectExpressions( collectionPersister, selections );
		determineValueSelectExpressions( collectionPersister, selections );

		String text = "";
		String[] columns = new String[selections.size()];
		for ( int i = 0; i < selections.size(); i++ ) {
			SelectExpression selectExpression = (SelectExpression) selections.get( i );
			text += ( ", " + selectExpression.getExpression() + " as " + selectExpression.getAlias() );
			columns[i] = selectExpression.getExpression();
		}

		text = text.substring( 2 ); //strip leading ", "
		setText( text );
		setResolved();
		return columns;
	}
