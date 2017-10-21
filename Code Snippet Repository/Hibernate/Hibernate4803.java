	private QualifiedTableName extractKeyTableName(ResultSet resultSet, String prefix) throws SQLException {
		final String incomingCatalogName = resultSet.getString( prefix + "TABLE_CAT" );
		final String incomingSchemaName = resultSet.getString( prefix + "TABLE_SCHEM" );
		final String incomingTableName = resultSet.getString( prefix + "TABLE_NAME" );

		final DatabaseIdentifier catalog = DatabaseIdentifier.toIdentifier( incomingCatalogName );
		final DatabaseIdentifier schema = DatabaseIdentifier.toIdentifier( incomingSchemaName );
		final DatabaseIdentifier table = DatabaseIdentifier.toIdentifier( incomingTableName );

		return new QualifiedTableName( catalog, schema, table );
	}
