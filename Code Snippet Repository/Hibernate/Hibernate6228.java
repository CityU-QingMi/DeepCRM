	@Test
	public void testNamedSessionFactorySerialization() throws Exception {
		Configuration cfg = new Configuration()
				.setProperty( AvailableSettings.SESSION_FACTORY_NAME, NAME )
				.setProperty( AvailableSettings.SESSION_FACTORY_NAME_IS_JNDI, "false" ); // default is true
		SessionFactory factory = cfg.buildSessionFactory();

		// we need to do some tricking here so that Hibernate thinks the deserialization happens in a
		// different VM
		String uuid = ( (SessionFactoryImplementor) factory ).getUuid();
		// deregister under this uuid...
		SessionFactoryRegistry.INSTANCE.removeSessionFactory( uuid, NAME, false, null );
		// and then register under a different uuid...
		SessionFactoryRegistry.INSTANCE.addSessionFactory( "some-other-uuid", NAME, false, factory, null );

		SessionFactory factory2 = (SessionFactory) SerializationHelper.clone( factory );
		assertSame( factory, factory2 );

		SessionFactoryRegistry.INSTANCE.removeSessionFactory( "some-other-uuid", NAME, false, null );
		factory.close();

		assertFalse( SessionFactoryRegistry.INSTANCE.hasRegistrations() );
	}
