	@Before
	public void setUp() throws SQLException {
		given(connection.createStatement()).willReturn(statement);
		given(connection.prepareStatement(anyString())).willReturn(preparedStatement);
		given(statement.executeQuery(anyString())).willReturn(resultSet);
		given(preparedStatement.executeQuery()).willReturn(resultSet);
		given(resultSet.next()).willReturn(true, true, false);
		given(resultSet.getString(1)).willReturn("tb1", "tb2");
		given(resultSet.getInt(2)).willReturn(1, 2);

		template.setDataSource(new SingleConnectionDataSource(connection, false));
		template.setExceptionTranslator(new SQLStateSQLExceptionTranslator());
		template.afterPropertiesSet();
	}
