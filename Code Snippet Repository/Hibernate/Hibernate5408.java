	@Test
	public void testExplicitShortNameUse() {
		final Map<String, String> configValues = new HashMap<String, String>();

		configValues.put( Environment.DIALECT, "H2" );
		assertEquals( H2Dialect.class, dialectFactory.buildDialect( configValues, null ).getClass() );

		configValues.put( Environment.DIALECT, "Oracle10g" );
		assertEquals( Oracle10gDialect.class, dialectFactory.buildDialect( configValues, null ).getClass() );
	}
