	@Test
	public void testHsqlMaxValueIncrementerWithDeleteSpecificValues() throws SQLException {
		given(dataSource.getConnection()).willReturn(connection);
		given(connection.createStatement()).willReturn(statement);
		given(statement.executeQuery("select max(identity()) from myseq")).willReturn(resultSet);
		given(resultSet.next()).willReturn(true);
		given(resultSet.getLong(1)).willReturn(0L, 1L, 2L, 3L, 4L, 5L);

		HsqlMaxValueIncrementer incrementer = new HsqlMaxValueIncrementer();
		incrementer.setDataSource(dataSource);
		incrementer.setIncrementerName("myseq");
		incrementer.setColumnName("seq");
		incrementer.setCacheSize(3);
		incrementer.setPaddingLength(3);
		incrementer.setDeleteSpecificValues(true);
		incrementer.afterPropertiesSet();

		assertEquals(0, incrementer.nextIntValue());
		assertEquals(1, incrementer.nextLongValue());
		assertEquals("002", incrementer.nextStringValue());
		assertEquals(3, incrementer.nextIntValue());
		assertEquals(4, incrementer.nextLongValue());

		verify(statement, times(6)).executeUpdate("insert into myseq values(null)");
		verify(statement).executeUpdate("delete from myseq where seq in (-1, 0, 1)");
		verify(statement).executeUpdate("delete from myseq where seq in (2, 3, 4)");
		verify(resultSet, times(6)).close();
		verify(statement, times(2)).close();
		verify(connection, times(2)).close();
	}
