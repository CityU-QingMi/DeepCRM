	@Test
	public void testJdbcDaoSupportWithDataSource() throws Exception {
		DataSource ds = mock(DataSource.class);
		final List<String> test = new ArrayList<>();
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			@Override
			protected void initDao() {
				test.add("test");
			}
		};
		dao.setDataSource(ds);
		dao.afterPropertiesSet();
		assertEquals("Correct DataSource", ds, dao.getDataSource());
		assertEquals("Correct JdbcTemplate", ds, dao.getJdbcTemplate().getDataSource());
		assertEquals("initDao called", test.size(), 1);
	}
