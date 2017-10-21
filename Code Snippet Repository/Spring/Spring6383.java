	@Test
	public void testQueryForListWithIntegerElement() throws Exception {
		String sql = "SELECT AGE FROM CUSTMR WHERE ID < 3";
		given(this.resultSet.next()).willReturn(true, false);
		given(this.resultSet.getInt(1)).willReturn(11);
		List<Integer> li = this.template.queryForList(sql, Integer.class);
		assertEquals("All rows returned", 1, li.size());
		assertEquals("Element is Integer", 11, li.get(0).intValue());
		verify(this.resultSet).close();
		verify(this.statement).close();
	}
