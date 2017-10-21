	public void bindColumnOrFormula(
			MappingDocument sourceDocument,
			RelationalValueSource relationalValueSource,
			SimpleValue simpleValue,
			boolean areColumnsNullableByDefault,
			ColumnNamingDelegate columnNamingDelegate) {
		bindColumnsAndFormulas(
				sourceDocument,
				Collections.singletonList( relationalValueSource ),
				simpleValue,
				areColumnsNullableByDefault,
				columnNamingDelegate
		);
	}
