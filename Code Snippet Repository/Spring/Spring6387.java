	@Test
	public void testQueryForObjectWithIntegerAndNull() throws Exception {
		String sql = "SELECT AGE FROM CUSTMR WHERE ID = 3";
		given(this.resultSet.next()).willReturn(true, false);
		given(this.resultSet.getInt(1)).willReturn(0);
		given(this.resultSet.wasNull()).willReturn(true);
		assertNull(this.template.queryForObject(sql, Integer.class));
		verify(this.resultSet).close();
		verify(this.statement).close();
	}
