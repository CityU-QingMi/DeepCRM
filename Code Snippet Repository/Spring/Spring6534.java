	@Test
	public void testInvalidClassName() throws Exception {
		String bogusClassName = "foobar";
		DriverManagerDataSource ds = new DriverManagerDataSource();
		try {
			ds.setDriverClassName(bogusClassName);
			fail("Should have thrown IllegalStateException");
		}
		catch (IllegalStateException ex) {
			// OK
			assertTrue(ex.getCause() instanceof ClassNotFoundException);
		}
	}
