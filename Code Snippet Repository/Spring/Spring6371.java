	@Test
	public void testQueryForLongPrimitive() throws Exception {
		String sql = "SELECT AGE FROM CUSTMR WHERE ID = 3";
		given(this.resultSet.next()).willReturn(true, false);
		given(this.resultSet.getLong(1)).willReturn(87L);
		long l = this.template.queryForObject(sql, long.class);
		assertEquals("Return of a long", 87, l);
		verify(this.resultSet).close();
		verify(this.statement).close();
	}
