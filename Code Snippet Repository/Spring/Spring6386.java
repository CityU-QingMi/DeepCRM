	@Test
	public void testQueryForObjectWithRowMapper() throws Exception {
		String sql = "SELECT AGE FROM CUSTMR WHERE ID = 3";
		given(this.resultSet.next()).willReturn(true, false);
		given(this.resultSet.getInt(1)).willReturn(22);
		Object o = this.template.queryForObject(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
		});
		assertTrue("Correct result type", o instanceof Integer);
		verify(this.resultSet).close();
		verify(this.statement).close();
	}
