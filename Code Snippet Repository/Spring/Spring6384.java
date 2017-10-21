	@Test
	public void testQueryForMapWithSingleRowAndColumn() throws Exception {
		String sql = "SELECT AGE FROM CUSTMR WHERE ID < 3";
		given(this.resultSet.next()).willReturn(true, false);
		given(this.resultSet.getObject(1)).willReturn(11);
		Map<String, Object> map = this.template.queryForMap(sql);
		assertEquals("Wow is Integer", 11, ((Integer) map.get("age")).intValue());
		verify(this.resultSet).close();
		verify(this.statement).close();
	}
