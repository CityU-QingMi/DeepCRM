	@Test
	public void createAndDestroyNestedWithH2() throws Exception {
		ClassPathXmlApplicationContext context = context("jdbc-destroy-nested-config-h2.xml");
		try {
			DataSource dataSource = context.getBean(DataSource.class);
			JdbcTemplate template = new JdbcTemplate(dataSource);
			assertNumRowsInTestTable(template, 1);
			context.getBean(EmbeddedDatabaseFactoryBean.class).destroy();
			expected.expect(BadSqlGrammarException.class); // Table has been dropped
			assertNumRowsInTestTable(template, 1);
		}
		finally {
			context.close();
		}
	}
