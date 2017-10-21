	public void bindColumns(
			MappingDocument sourceDocument,
			List<ColumnSource> columnSources,
			SimpleValue simpleValue,
			boolean areColumnsNullableByDefault,
			ColumnNamingDelegate columnNamingDelegate) {
		for ( ColumnSource columnSource : columnSources ) {
			bindColumn(
					sourceDocument,
					columnSource,
					simpleValue,
					areColumnsNullableByDefault,
					columnNamingDelegate
			);
		}
	}
