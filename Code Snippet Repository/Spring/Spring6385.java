	@Test
	public void testQueryForObjectThrowsIncorrectResultSizeForMoreThanOneRow() throws Exception {
		String sql = "select pass from t_account where first_name='Alef'";
		given(this.resultSet.next()).willReturn(true, true, false);
		given(this.resultSet.getString(1)).willReturn("pass");
		this.thrown.expect(IncorrectResultSizeDataAccessException.class);
		try {
			this.template.queryForObject(sql, String.class);
		}
		finally {
			verify(this.resultSet).close();
			verify(this.statement).close();
		}
	}
