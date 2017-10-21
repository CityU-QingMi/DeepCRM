	@Override
	public void buildEntityManagerFactory() throws Exception {
		try {
			super.buildEntityManagerFactory();
			fail("Should halt on error!");
		}
		catch ( Exception e ) {
			SchemaManagementException cause = (SchemaManagementException) e.getCause();
			assertEquals("Halting on error : Error executing DDL via JDBC Statement", cause.getMessage());
		}
	}
