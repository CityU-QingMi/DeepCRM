		@SuppressWarnings("")
		public Mock(MockType type) throws Exception {
			connection = mock(Connection.class);
			statement = mock(Statement.class);
			resultSet = mock(ResultSet.class);
			resultSetMetaData = mock(ResultSetMetaData.class);

			given(connection.createStatement()).willReturn(statement);
			given(statement.executeQuery(anyString())).willReturn(resultSet);
			given(resultSet.getMetaData()).willReturn(resultSetMetaData);

			given(resultSet.next()).willReturn(true, false);
			given(resultSet.getString(1)).willReturn("Bubba");
			given(resultSet.getLong(2)).willReturn(22L);
			given(resultSet.getTimestamp(3)).willReturn(new Timestamp(1221222L));
			given(resultSet.getObject(anyInt(), any(Class.class))).willThrow(new SQLFeatureNotSupportedException());
			given(resultSet.getDate(3)).willReturn(new java.sql.Date(1221222L));
			given(resultSet.getBigDecimal(4)).willReturn(new BigDecimal("1234.56"));
			given(resultSet.wasNull()).willReturn(type == MockType.TWO);

			given(resultSetMetaData.getColumnCount()).willReturn(4);
			given(resultSetMetaData.getColumnLabel(1)).willReturn(
					type == MockType.THREE ? "Last Name" : "name");
			given(resultSetMetaData.getColumnLabel(2)).willReturn("age");
			given(resultSetMetaData.getColumnLabel(3)).willReturn("birth_date");
			given(resultSetMetaData.getColumnLabel(4)).willReturn("balance");

			jdbcTemplate = new JdbcTemplate();
			jdbcTemplate.setDataSource(new SingleConnectionDataSource(connection, false));
			jdbcTemplate.setExceptionTranslator(new SQLStateSQLExceptionTranslator());
			jdbcTemplate.afterPropertiesSet();
		}
