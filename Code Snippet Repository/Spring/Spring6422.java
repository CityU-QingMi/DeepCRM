	@Test
	public void testStringsWithPreparedStatementSetter() throws Exception {
		final Integer argument = 99;
		doTestStrings(null, null, null, argument, new JdbcTemplateCallback() {
			@Override
			public void doInJdbcTemplate(JdbcTemplate template, String sql, RowCallbackHandler rch) {
				template.query(sql, new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setObject(1, argument);
					}
				}, rch);
			}
		});
	}
