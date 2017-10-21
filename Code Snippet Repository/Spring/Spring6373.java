	@Test
	public void testQueryForListWithArgsAndEmptyResult() throws Exception {
		String sql = "SELECT AGE FROM CUSTMR WHERE ID < ?";
		given(this.resultSet.next()).willReturn(false);
		List<Map<String, Object>> li = this.template.queryForList(sql, new Object[] {3});
		assertEquals("All rows returned", 0, li.size());
		verify(this.preparedStatement).setObject(1, 3);
		verify(this.resultSet).close();
		verify(this.preparedStatement).close();
	}
