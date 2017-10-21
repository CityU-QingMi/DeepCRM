	@Test
	public void testStringsWithPreparedStatementArgs() throws Exception {
		final Integer argument = 99;
		doTestStrings(null, null, null, argument, new JdbcTemplateCallback() {
			@Override
			public void doInJdbcTemplate(JdbcTemplate template, String sql, RowCallbackHandler rch) {
				template.query(sql, new Object[] { argument }, rch);
			}
		});
	}
