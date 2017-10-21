	@Test
	public void testTableWithSingleColumnGeneratedKey() throws Exception {
		final String TABLE = "customers";
		final String USER = "me";

		ResultSet metaDataResultSet = mock(ResultSet.class);
		given(metaDataResultSet.next()).willReturn(true, false);
		given(metaDataResultSet.getString("TABLE_SCHEM")).willReturn(USER);
		given(metaDataResultSet.getString("TABLE_NAME")).willReturn(TABLE);
		given(metaDataResultSet.getString("TABLE_TYPE")).willReturn("TABLE");

		ResultSet columnsResultSet = mock(ResultSet.class);
		given(columnsResultSet.next()).willReturn(true, false);
		given(columnsResultSet.getString("COLUMN_NAME")).willReturn("id");
		given(columnsResultSet.getInt("DATA_TYPE")).willReturn(Types.INTEGER);
		given(columnsResultSet.getBoolean("NULLABLE")).willReturn(false);

		given(databaseMetaData.getDatabaseProductName()).willReturn("MyDB");
		given(databaseMetaData.getDatabaseProductName()).willReturn("1.0");
		given(databaseMetaData.getUserName()).willReturn(USER);
		given(databaseMetaData.storesLowerCaseIdentifiers()).willReturn(true);
		given(databaseMetaData.getTables(null, null, TABLE, null)).willReturn(metaDataResultSet);
		given(databaseMetaData.getColumns(null, USER, TABLE, null)).willReturn(columnsResultSet);

		MapSqlParameterSource map = new MapSqlParameterSource();
		String[] keyCols = new String[] { "id" };
		context.setTableName(TABLE);
		context.processMetaData(dataSource, new ArrayList<>(), keyCols);
		List<Object> values = context.matchInParameterValuesWithInsertColumns(map);
		String insertString = context.createInsertString(keyCols);

		assertEquals("wrong number of parameters: ", 0, values.size());
		assertEquals("empty insert not generated correctly", "INSERT INTO customers () VALUES()", insertString);
		verify(metaDataResultSet, atLeastOnce()).next();
		verify(columnsResultSet, atLeastOnce()).next();
		verify(metaDataResultSet).close();
		verify(columnsResultSet).close();
	}
