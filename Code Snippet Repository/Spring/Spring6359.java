	private void assertCorrectSetupAndCloseContext(String file, int count, String... dataSources) {
		ConfigurableApplicationContext context = context(file);
		try {
			for (String dataSourceName : dataSources) {
				DataSource dataSource = context.getBean(dataSourceName, DataSource.class);
				assertNumRowsInTestTable(new JdbcTemplate(dataSource), count);
				assertTrue(dataSource instanceof AbstractDriverBasedDataSource);
				AbstractDriverBasedDataSource adbDataSource = (AbstractDriverBasedDataSource) dataSource;
				assertThat(adbDataSource.getUrl(), containsString(dataSourceName));
			}
		}
		finally {
			context.close();
		}
	}
