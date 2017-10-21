	@Test
	public void createAndDestroy() throws Exception {
		ClassPathXmlApplicationContext context = context("jdbc-destroy-config.xml");
		try {
			DataSource dataSource = context.getBean(DataSource.class);
			JdbcTemplate template = new JdbcTemplate(dataSource);
			assertNumRowsInTestTable(template, 1);
			context.getBean(DataSourceInitializer.class).destroy();
			expected.expect(BadSqlGrammarException.class); // Table has been dropped
			assertNumRowsInTestTable(template, 1);
		}
		finally {
			context.close();
		}
	}
