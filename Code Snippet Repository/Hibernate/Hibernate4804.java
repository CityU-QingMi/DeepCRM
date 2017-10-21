	private QualifiedTableName extractTableName(ResultSet resultSet) throws SQLException {
		final String incomingCatalogName = resultSet.getString( "TABLE_CAT" );
		final String incomingSchemaName = resultSet.getString( "TABLE_SCHEM" );
		final String incomingTableName = resultSet.getString( "TABLE_NAME" );

		final DatabaseIdentifier catalog = DatabaseIdentifier.toIdentifier( incomingCatalogName );
		final DatabaseIdentifier schema = DatabaseIdentifier.toIdentifier( incomingSchemaName );
		final DatabaseIdentifier table = DatabaseIdentifier.toIdentifier( incomingTableName );

		return new QualifiedTableName( catalog, schema, table );
	}
