	private void populateTablesWithColumns(
			String catalogFilter,
			String schemaFilter,
			NameSpaceTablesInformation tables) {
		try {
			ResultSet resultSet = extractionContext.getJdbcDatabaseMetaData().getColumns(
					catalogFilter,
					schemaFilter,
					null,
					"%"
			);
			try {
				String currentTableName = "";
				TableInformation currentTable = null;
				while ( resultSet.next() ) {
					if ( !currentTableName.equals( resultSet.getString( "TABLE_NAME" ) ) ) {
						currentTableName = resultSet.getString( "TABLE_NAME" );
						currentTable = tables.getTableInformation( currentTableName );
					}
					if ( currentTable != null ) {
						final ColumnInformationImpl columnInformation = new ColumnInformationImpl(
								currentTable,
								DatabaseIdentifier.toIdentifier( resultSet.getString( "COLUMN_NAME" ) ),
								resultSet.getInt( "DATA_TYPE" ),
								new StringTokenizer( resultSet.getString( "TYPE_NAME" ), "() " ).nextToken(),
								resultSet.getInt( "COLUMN_SIZE" ),
								resultSet.getInt( "DECIMAL_DIGITS" ),
								interpretTruthValue( resultSet.getString( "IS_NULLABLE" ) )
						);
						currentTable.addColumn( columnInformation );
					}
				}
			}
			finally {
				resultSet.close();
			}
		}
		catch (SQLException e) {
			throw convertSQLException(
					e,
					"Error accessing tables metadata"
			);
		}
	}
