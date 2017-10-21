	@Test
	public void testWithSameRegisteredSessionFactory() throws Exception {
		Configuration cfg = new Configuration()
				.setProperty( AvailableSettings.SESSION_FACTORY_NAME, NAME )
				.setProperty( AvailableSettings.SESSION_FACTORY_NAME_IS_JNDI, "false" ); // default is true
		SessionFactoryImplementor factory = (SessionFactoryImplementor) cfg.buildSessionFactory();

		// Session factory is registered.
		assertSame( factory, SessionFactoryRegistry.INSTANCE.getNamedSessionFactory( NAME ) );

		TypeFactory typeFactory = factory.getTypeResolver().getTypeFactory();
		byte[] typeFactoryBytes = SerializationHelper.serialize( typeFactory );
		typeFactory = (TypeFactory) SerializationHelper.deserialize( typeFactoryBytes );

		assertSame( factory, typeFactory.resolveSessionFactory() );
		factory.close();
	}
