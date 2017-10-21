    @Test
    public void testDefaultPropertyAccessIsInherited() throws Exception {
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass( Horse.class );
        cfg.addAnnotatedClass( Animal.class );

        SessionFactoryImplementor factory = (SessionFactoryImplementor) cfg.buildSessionFactory( serviceRegistry );
		try {
			EntityTuplizer tuplizer = factory.getEntityPersister( Animal.class.getName() )
					.getEntityMetamodel()
					.getTuplizer();
			assertTrue(
					"Property access should be used since explicity configured via @Access",
					tuplizer.getIdentifierGetter() instanceof GetterMethodImpl
			);

			tuplizer = factory.getEntityPersister( Horse.class.getName() )
					.getEntityMetamodel()
					.getTuplizer();
			assertTrue(
					"Field access should be used since the default access mode gets inherited",
					tuplizer.getGetter( 0 ) instanceof GetterFieldImpl
			);
		}
		finally {
			factory.close();
		}
	}
