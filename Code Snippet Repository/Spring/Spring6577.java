	@Test
	public void parameterPropagation() {
		SqlOperation operation = new SqlOperation() {};
		DataSource ds = new DriverManagerDataSource();
		operation.setDataSource(ds);
		operation.setFetchSize(10);
		operation.setMaxRows(20);
		JdbcTemplate jt = operation.getJdbcTemplate();
		assertEquals(ds, jt.getDataSource());
		assertEquals(10, jt.getFetchSize());
		assertEquals(20, jt.getMaxRows());
	}
