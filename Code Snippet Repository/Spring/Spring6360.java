	private void assertCorrectSetupForSingleDataSource(String file, Predicate<String> urlPredicate) {
		ConfigurableApplicationContext context = context(file);
		try {
			DataSource dataSource = context.getBean(DataSource.class);
			assertNumRowsInTestTable(new JdbcTemplate(dataSource), 1);
			assertTrue(dataSource instanceof AbstractDriverBasedDataSource);
			AbstractDriverBasedDataSource adbDataSource = (AbstractDriverBasedDataSource) dataSource;
			assertTrue(urlPredicate.test(adbDataSource.getUrl()));
		}
		finally {
			context.close();
		}
	}
