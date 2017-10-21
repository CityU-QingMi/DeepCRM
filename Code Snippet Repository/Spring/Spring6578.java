	@Test
	public void parametersSetWithList() {
		DataSource ds = new DriverManagerDataSource();
		operation.setDataSource(ds);
		operation.setSql("select * from mytable where one = ? and two = ?");
		operation.setParameters(new SqlParameter[] {
				new SqlParameter("one", Types.NUMERIC),
				new SqlParameter("two", Types.NUMERIC)});
		operation.afterPropertiesSet();
		operation.validateParameters(new Object[] { 1, "2" });
		assertEquals(2, operation.getDeclaredParameters().size());
	}
