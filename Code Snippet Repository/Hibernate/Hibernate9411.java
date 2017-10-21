	@Test
	public void testUnregisterSerializeRegisterDiffSessionFactoryNoName() throws Exception {
		Configuration cfg = new Configuration();
		SessionFactoryImplementor factory = (SessionFactoryImplementor) cfg.buildSessionFactory();
		assertSame( factory, SessionFactoryRegistry.INSTANCE.getSessionFactory( factory.getUuid() ) );

		// Remove the session factory from the registry
		SessionFactoryRegistry.INSTANCE.removeSessionFactory( factory.getUuid(), null, false, null );
		assertNull( SessionFactoryRegistry.INSTANCE.getSessionFactory( factory.getUuid() ) );

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

		// Now create a new session factory with the same name; it will have a different UUID.
		SessionFactoryImplementor factoryWithDiffUuid = (SessionFactoryImplementor) cfg.buildSessionFactory();
		assertSame( factoryWithDiffUuid, SessionFactoryRegistry.INSTANCE.getSessionFactory( factoryWithDiffUuid.getUuid() ) );
		assertFalse( factory.getUuid().equals( factoryWithDiffUuid.getUuid() ) );

		// It should not be possible to resolve the session factory with no name configured.
		try {
			typeFactory.resolveSessionFactory();
			fail( "should have failed with HibernateException because session factories were not registered with the same non-null name." );
		}
		catch ( HibernateException ex ) {
			// expected
		}

		factory.close();
		factoryWithDiffUuid.close();
	}
