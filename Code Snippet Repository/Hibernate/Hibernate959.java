	public void bindColumnsAndFormulas(
			MappingDocument sourceDocument,
			List<RelationalValueSource> relationalValueSources,
			SimpleValue simpleValue,
			boolean areColumnsNullableByDefault,
			ColumnNamingDelegate columnNamingDelegate) {
		for ( RelationalValueSource relationalValueSource : relationalValueSources ) {
			if ( ColumnSource.class.isInstance( relationalValueSource ) ) {
				final ColumnSource columnSource = (ColumnSource) relationalValueSource;
				bindColumn(
						sourceDocument,
						columnSource,
						simpleValue,
						areColumnsNullableByDefault,
						columnNamingDelegate
				);
			}
			else {
				final DerivedValueSource formulaSource = (DerivedValueSource) relationalValueSource;
				simpleValue.addFormula( new Formula( formulaSource.getExpression() ) );
			}
		}
	}
