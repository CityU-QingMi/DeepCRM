	@Test
	public void testQueryForInt() throws Exception {
		String sql = "SELECT AGE FROM CUSTMR WHERE ID = 3";
		given(this.resultSet.next()).willReturn(true, false);
		given(this.resultSet.getInt(1)).willReturn(22);
		int i = this.template.queryForObject(sql, Integer.class).intValue();
		assertEquals("Return of an int", 22, i);
		verify(this.resultSet).close();
		verify(this.statement).close();
	}
