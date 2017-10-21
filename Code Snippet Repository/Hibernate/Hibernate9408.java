	@Test
	public void testUnregisterSerializeRegisterSameSessionFactory() throws Exception {
		Configuration cfg = new Configuration()
				.setProperty( AvailableSettings.SESSION_FACTORY_NAME, NAME )
				.setProperty( AvailableSettings.SESSION_FACTORY_NAME_IS_JNDI, "false" ); // default is true
		SessionFactoryImplementor factory = (SessionFactoryImplementor) cfg.buildSessionFactory();
		assertSame( factory, SessionFactoryRegistry.INSTANCE.getNamedSessionFactory( NAME ) );

		// Remove the session factory from the registry
		SessionFactoryRegistry.INSTANCE.removeSessionFactory( factory.getUuid(), NAME, false, null );
		assertNull( SessionFactoryRegistry.INSTANCE.findSessionFactory( factory.getUuid(), NAME ) );

		TypeFactory typeFactory = factory.getTypeResolver().getTypeFactory();
		byte[] typeFactoryBytes = SerializationHelper.serialize( typeFactory );
		typeFactory = (TypeFactory) SerializationHelper.deserialize( typeFactoryBytes );

		try {
			typeFactory.resolveSessionFactory();
			fail( "should have failed with HibernateException because session factory is not registered." );
		}
		catch ( HibernateException ex ) {
			// expected because the session factory is not registered.
		}

		// Re-register the same session factory.
		SessionFactoryRegistry.INSTANCE.addSessionFactory( factory.getUuid(), NAME, false, factory, null );

		// Session factory resolved from typeFactory should be the new session factory
		// (because it is resolved from SessionFactoryRegistry.INSTANCE)
		assertSame( factory, typeFactory.resolveSessionFactory() );
		factory.close();
	}
