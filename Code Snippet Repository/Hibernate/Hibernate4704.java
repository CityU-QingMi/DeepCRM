	private AST processSqlValueReference(SqlValueReference sqlValueReference) {
		if ( ColumnReference.class.isInstance( sqlValueReference ) ) {
			final String columnName = ( (ColumnReference) sqlValueReference ).getColumnName();
			return getASTFactory().create( OrderByTemplateTokenTypes.IDENT, makeColumnReference( columnName ) );
		}
		else {
			final String formulaFragment = ( (FormulaReference) sqlValueReference ).getFormulaFragment();
			// formulas have already been "adjusted" for aliases by appending Template.TEMPLATE to places
			// where we believe column references are.  Fixing that is beyond scope of this work.  But we need
			// to re-adjust that to use the order-by expectation of wrapping the column names in curly
			// braces (i.e., `{column_name}`).
			final String adjustedText = adjustTemplateReferences( formulaFragment );
			return getASTFactory().create( OrderByTemplateTokenTypes.IDENT, adjustedText );
		}
	}
