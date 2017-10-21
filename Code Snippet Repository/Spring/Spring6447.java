	@Test
	public void testBatchUpdateWithSqlParameterSourcePlusTypeInfo() throws Exception {
		SqlParameterSource[] ids = new SqlParameterSource[2];
		ids[0] = new MapSqlParameterSource().addValue("id", 100, Types.NUMERIC);
		ids[1] = new MapSqlParameterSource().addValue("id", 200, Types.NUMERIC);
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
		verify(preparedStatement).setObject(1, 100, Types.NUMERIC);
		verify(preparedStatement).setObject(1, 200, Types.NUMERIC);
		verify(preparedStatement, times(2)).addBatch();
		verify(preparedStatement, atLeastOnce()).close();
		verify(connection, atLeastOnce()).close();
	}
