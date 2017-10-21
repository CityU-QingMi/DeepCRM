	@Test
	public void testQueryForObjectWithArgsAndRowMapper() throws Exception {
		String sql = "SELECT AGE FROM CUSTMR WHERE ID = ?";
		given(this.resultSet.next()).willReturn(true, false);
		given(this.resultSet.getInt(1)).willReturn(22);
		Object o = this.template.queryForObject(sql, new Object[] {3}, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
		});
		assertTrue("Correct result type", o instanceof Integer);
		verify(this.preparedStatement).setObject(1, 3);
		verify(this.resultSet).close();
		verify(this.preparedStatement).close();
	}
