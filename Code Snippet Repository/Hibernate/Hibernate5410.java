	@Test
	public void testBuildDialectByProperties() {
		Properties props = new Properties();

		try {
			dialectFactory.buildDialect( props, null );
			fail();
		}
		catch ( HibernateException e ) {
			assertNull( e.getCause() );
		}

		props.setProperty( Environment.DIALECT, "org.hibernate.dialect.HSQLDialect" );
		assertEquals( HSQLDialect.class, dialectFactory.buildDialect( props, null ).getClass() );
	}
