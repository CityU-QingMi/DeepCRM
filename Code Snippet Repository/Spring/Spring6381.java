	@Test
	public void testQueryForLongWithArgs() throws Exception {
		String sql = "SELECT AGE FROM CUSTMR WHERE ID = ?";
		given(this.resultSet.next()).willReturn(true, false);
		given(this.resultSet.getLong(1)).willReturn(87L);
		long l = this.template.queryForObject(sql, new Object[] {3}, Long.class).longValue();
		assertEquals("Return of a long", 87, l);
		verify(this.preparedStatement).setObject(1, 3);
		verify(this.resultSet).close();
		verify(this.preparedStatement).close();
	}
