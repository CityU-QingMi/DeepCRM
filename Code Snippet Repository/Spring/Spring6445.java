	@Test
	public void testBatchUpdateWithPlainMap() throws Exception {
		@SuppressWarnings("unchecked")
		final Map<String, Integer>[] ids = new Map[2];
		ids[0] = Collections.singletonMap("id", 100);
		ids[1] = Collections.singletonMap("id", 200);
		final int[] rowsAffected = new int[] { 1, 2 };

		given(preparedStatement.executeBatch()).willReturn(rowsAffected);
		given(connection.getMetaData()).willReturn(databaseMetaData);

		JdbcTemplate template = new JdbcTemplate(dataSource, false);
		namedParameterTemplate = new NamedParameterJdbcTemplate(template);
		int[] actualRowsAffected = namedParameterTemplate.batchUpdate("UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = :id", ids);

		assertTrue("executed 2 updates", actualRowsAffected.length == 2);
		assertEquals(rowsAffected[0], actualRowsAffected[0]);
		assertEquals(rowsAffected[1], actualRowsAffected[1]);
		verify(connection).prepareStatement("UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = ?");
		verify(preparedStatement).setObject(1, 100);
		verify(preparedStatement).setObject(1, 200);
		verify(preparedStatement, times(2)).addBatch();
		verify(preparedStatement, atLeastOnce()).close();
		verify(connection, atLeastOnce()).close();
	}
