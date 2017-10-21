	@Test
	public void testUnNamedSessionFactorySerialization() throws Exception {
		// IMPL NOTE : this test is a control to testNamedSessionFactorySerialization
		// 		here, the test should fail based just on attempted uuid resolution
		Configuration cfg = new Configuration()
				.setProperty( AvailableSettings.SESSION_FACTORY_NAME_IS_JNDI, "false" ); // default is true
		SessionFactory factory = cfg.buildSessionFactory();

		// we need to do some tricking here so that Hibernate thinks the deserialization happens in a
		// different VM
		String uuid = ( (SessionFactoryImplementor) factory ).getUuid();
		// deregister under this uuid...
		SessionFactoryRegistry.INSTANCE.removeSessionFactory( uuid, null, false, null );
		// and then register under a different uuid...
		SessionFactoryRegistry.INSTANCE.addSessionFactory( "some-other-uuid", null, false, factory, null );

		try {
			SerializationHelper.clone( factory );
			fail( "Expecting an error" );
		}
		catch ( SerializationException expected ) {
		}

		SessionFactoryRegistry.INSTANCE.removeSessionFactory( "some-other-uuid", null, false, null );
		factory.close();

		assertFalse( SessionFactoryRegistry.INSTANCE.hasRegistrations() );
	}
