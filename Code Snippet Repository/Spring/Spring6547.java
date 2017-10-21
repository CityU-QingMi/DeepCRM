	@Test
	public void testFactoryBeanLifecycle() throws Exception {
		EmbeddedDatabaseFactoryBean bean = new EmbeddedDatabaseFactoryBean();
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator(resource("db-schema.sql"),
			resource("db-test-data.sql"));
		bean.setDatabasePopulator(populator);
		bean.afterPropertiesSet();
		DataSource ds = bean.getObject();
		JdbcTemplate template = new JdbcTemplate(ds);
		assertEquals("Keith", template.queryForObject("select NAME from T_TEST", String.class));
		bean.destroy();
	}
