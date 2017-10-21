	private TableInformation extractTableInformation(ResultSet resultSet) throws SQLException {
		final QualifiedTableName tableName = extractTableName( resultSet );

		final TableInformationImpl tableInformation = new TableInformationImpl(
				this,
				identifierHelper(),
				tableName,
				isPhysicalTableType( resultSet.getString( "TABLE_TYPE" ) ),
				resultSet.getString( "REMARKS" )
		);
		return tableInformation;
	}
