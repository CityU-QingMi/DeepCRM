	@Test
	public void testQueryForListWithSingleRowAndColumn() throws Exception {
		String sql = "SELECT AGE FROM CUSTMR WHERE ID < 3";
		given(this.resultSet.next()).willReturn(true, false);
		given(this.resultSet.getObject(1)).willReturn(11);
		List<Map<String, Object>> li = this.template.queryForList(sql);
		assertEquals("All rows returned", 1, li.size());
		assertEquals("First row is Integer", 11, ((Integer) li.get(0).get("age")).intValue());
		verify(this.resultSet).close();
		verify(this.statement).close();
	}
