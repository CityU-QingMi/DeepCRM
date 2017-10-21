	@Test
	public void testUnregisterSerializeRegisterSameSessionFactoryNoName() throws Exception {
		Configuration cfg = new Configuration();
		SessionFactoryImplementor factory = (SessionFactoryImplementor) cfg.buildSessionFactory();
		assertSame( factory, SessionFactoryRegistry.INSTANCE.findSessionFactory( factory.getUuid(), null ) );

		// Remove the session factory from the registry
		SessionFactoryRegistry.INSTANCE.removeSessionFactory( factory.getUuid(), null, false, null );
		assertNull( SessionFactoryRegistry.INSTANCE.findSessionFactory( factory.getUuid(), null ) );

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
		SessionFactoryRegistry.INSTANCE.addSessionFactory( factory.getUuid(), null, false, factory, null );

		// Session factory resolved from typeFactory should be the new session factory
		// (because it is resolved from SessionFactoryRegistry.INSTANCE)
		assertSame( factory, typeFactory.resolveSessionFactory() );
		factory.close();
	}
