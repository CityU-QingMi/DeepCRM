	@Test
	public void schedulerWithHsqlDataSource() throws Exception {
		// Assume.group(TestGroup.PERFORMANCE);

		DummyJob.param = 0;
		DummyJob.count = 0;

		ClassPathXmlApplicationContext ctx = context("databasePersistence.xml");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ctx.getBean(DataSource.class));
		assertFalse("No triggers were persisted", jdbcTemplate.queryForList("SELECT * FROM qrtz_triggers").isEmpty());

/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
	}
