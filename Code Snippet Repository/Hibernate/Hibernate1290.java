	private static Ejb3Column[] buildImplicitColumn(
			PropertyData inferredData,
			String suffixForDefaultColumnName,
			Map<String, Join> secondaryTables,
			PropertyHolder propertyHolder,
			Nullability nullability,
			MetadataBuildingContext context) {
		Ejb3Column column = new Ejb3Column();
		Ejb3Column[] columns = new Ejb3Column[1];
		columns[0] = column;

		//not following the spec but more clean
		if ( nullability != Nullability.FORCED_NULL
				&& inferredData.getClassOrElement().isPrimitive()
				&& !inferredData.getProperty().isArray() ) {
			column.setNullable( false );
		}
		column.setLength( DEFAULT_COLUMN_LENGTH );
		final String propertyName = inferredData.getPropertyName();
		column.setPropertyName(
				BinderHelper.getRelativePath( propertyHolder, propertyName )
		);
		column.setPropertyHolder( propertyHolder );
		column.setJoins( secondaryTables );
		column.setBuildingContext( context );

		// property name + suffix is an "explicit" column name
		if ( !StringHelper.isEmpty( suffixForDefaultColumnName ) ) {
			column.setLogicalColumnName( propertyName + suffixForDefaultColumnName );
			column.setImplicit( false );
		}
		else {
			column.setImplicit( true );
		}
		applyColumnDefault( column, inferredData );
		column.extractDataFromPropertyData( inferredData );
		column.bind();
		return columns;
	}
