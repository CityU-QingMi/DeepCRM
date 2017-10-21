    @Test
    public void testDefaultFieldAccessIsInherited() throws Exception {
        Configuration cfg = new Configuration();
        Class<?> classUnderTest = User.class;
        cfg.addAnnotatedClass( classUnderTest );
        cfg.addAnnotatedClass( Person.class );
        cfg.addAnnotatedClass( Being.class );
        SessionFactoryImplementor factory = (SessionFactoryImplementor) cfg.buildSessionFactory( serviceRegistry );
		try {
			EntityTuplizer tuplizer = factory.getEntityPersister( classUnderTest.getName() )
					.getEntityMetamodel()
					.getTuplizer();
			assertTrue(
					"Field access should be used since the default access mode gets inherited",
					tuplizer.getIdentifierGetter() instanceof GetterFieldImpl
			);
		}
		finally {
			factory.close();
		}
	}
