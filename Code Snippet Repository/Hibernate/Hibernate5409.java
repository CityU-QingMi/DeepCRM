	@Test
	public void testExplicitlySuppliedDialectClassName() {
		final Map<String, String> configValues = new HashMap<String, String>();

		configValues.put( Environment.DIALECT, "org.hibernate.dialect.HSQLDialect" );
		assertEquals( HSQLDialect.class, dialectFactory.buildDialect( configValues, null ).getClass() );

		configValues.put( Environment.DIALECT, "org.hibernate.dialect.NoSuchDialect" );
		try {
			dialectFactory.buildDialect( configValues, null );
			fail();
		}
		catch ( HibernateException e ) {
			assertEquals( "unexpected exception type", StrategySelectionException.class, e.getClass() );
		}

		configValues.put( Environment.DIALECT, "java.lang.Object" );
		try {
			dialectFactory.buildDialect( configValues, null );
			fail();
		}
		catch ( HibernateException e ) {
			assertEquals( "unexpected exception type", ClassCastException.class, e.getCause().getClass() );
		}
	}
