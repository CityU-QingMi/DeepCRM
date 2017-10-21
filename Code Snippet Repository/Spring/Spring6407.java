	@Test
	public void testBatchUpdateWithListOfObjectArraysPlusTypeInfo() throws Exception {
		final String sql = "UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = ?";
		final List<Object[]> ids = new ArrayList<>();
		ids.add(new Object[] {100});
		ids.add(new Object[] {200});
		final int[] sqlTypes = new int[] {Types.NUMERIC};
		final int[] rowsAffected = new int[] { 1, 2 };

		given(this.preparedStatement.executeBatch()).willReturn(rowsAffected);
		mockDatabaseMetaData(true);

		this.template = new JdbcTemplate(this.dataSource, false);
		int[] actualRowsAffected = this.template.batchUpdate(sql, ids, sqlTypes);

		assertTrue("executed 2 updates", actualRowsAffected.length == 2);
		assertEquals(rowsAffected[0], actualRowsAffected[0]);
		assertEquals(rowsAffected[1], actualRowsAffected[1]);
		verify(this.preparedStatement, times(2)).addBatch();
		verify(this.preparedStatement).setObject(1, 100, sqlTypes[0]);
		verify(this.preparedStatement).setObject(1, 200, sqlTypes[0]);
		verify(this.preparedStatement).close();
		verify(this.connection, atLeastOnce()).close();
	}
