	@Test
	public void testPostgreSQLSequenceMaxValueIncrementer() throws SQLException {
		given(dataSource.getConnection()).willReturn(connection);
		given(connection.createStatement()).willReturn(statement);
		given(statement.executeQuery("select nextval('myseq')")).willReturn(resultSet);
		given(resultSet.next()).willReturn(true);
		given(resultSet.getLong(1)).willReturn(10L, 12L);

		PostgreSQLSequenceMaxValueIncrementer incrementer = new PostgreSQLSequenceMaxValueIncrementer();
		incrementer.setDataSource(dataSource);
		incrementer.setIncrementerName("myseq");
		incrementer.setPaddingLength(5);
		incrementer.afterPropertiesSet();

		assertEquals("00010", incrementer.nextStringValue());
		assertEquals(12, incrementer.nextIntValue());

		verify(resultSet, times(2)).close();
		verify(statement, times(2)).close();
		verify(connection, times(2)).close();
	}
