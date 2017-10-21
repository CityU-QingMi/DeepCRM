	@Test
	public void testMySQLMaxValueIncrementer() throws SQLException {
		given(dataSource.getConnection()).willReturn(connection);
		given(connection.createStatement()).willReturn(statement);
		given(statement.executeQuery("select last_insert_id()")).willReturn(resultSet);
		given(resultSet.next()).willReturn(true);
		given(resultSet.getLong(1)).willReturn(2L, 4L);

		MySQLMaxValueIncrementer incrementer = new MySQLMaxValueIncrementer();
		incrementer.setDataSource(dataSource);
		incrementer.setIncrementerName("myseq");
		incrementer.setColumnName("seq");
		incrementer.setCacheSize(2);
		incrementer.setPaddingLength(1);
		incrementer.afterPropertiesSet();

		assertEquals(1, incrementer.nextIntValue());
		assertEquals(2, incrementer.nextLongValue());
		assertEquals("3", incrementer.nextStringValue());
		assertEquals(4, incrementer.nextLongValue());

		verify(statement, times(2)).executeUpdate("update myseq set seq = last_insert_id(seq + 2)");
		verify(resultSet, times(2)).close();
		verify(statement, times(2)).close();
		verify(connection, times(2)).close();
	}
