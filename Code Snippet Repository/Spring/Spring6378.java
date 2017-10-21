	@Test
	public void testQueryForObjectWithArgsAndInteger() throws Exception {
		String sql = "SELECT AGE FROM CUSTMR WHERE ID = ?";
		given(this.resultSet.next()).willReturn(true, false);
		given(this.resultSet.getInt(1)).willReturn(22);
		Object o = this.template.queryForObject(sql, new Object[] {3}, Integer.class);
		assertTrue("Correct result type", o instanceof Integer);
		verify(this.preparedStatement).setObject(1, 3);
		verify(this.resultSet).close();
		verify(this.preparedStatement).close();
	}
