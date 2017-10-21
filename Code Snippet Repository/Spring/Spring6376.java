	@Test
	public void testQueryForMapWithArgsAndSingleRowAndColumn() throws Exception {
		String sql = "SELECT AGE FROM CUSTMR WHERE ID < ?";
		given(this.resultSet.next()).willReturn(true, false);
		given(this.resultSet.getObject(1)).willReturn(11);
		Map<String, Object> map = this.template.queryForMap(sql, new Object[] {3});
		assertEquals("Row is Integer", 11, ((Integer) map.get("age")).intValue());
		verify(this.preparedStatement).setObject(1, 3);
		verify(this.resultSet).close();
		verify(this.preparedStatement).close();
	}
