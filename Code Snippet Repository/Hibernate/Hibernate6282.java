    @Test
    public void testExplicitPropertyAccessAnnotationsWithJpaStyleOverride() throws Exception {
        Configuration cfg = new Configuration();
        Class<?> classUnderTest = Course5.class;
        cfg.addAnnotatedClass( classUnderTest );
        cfg.addAnnotatedClass( Student.class );
        SessionFactoryImplementor factory = (SessionFactoryImplementor) cfg.buildSessionFactory( serviceRegistry );
		try {
			EntityTuplizer tuplizer = factory.getEntityPersister( classUnderTest.getName() )
					.getEntityMetamodel()
					.getTuplizer();
			assertTrue(
					"Field access should be used.",
					tuplizer.getIdentifierGetter() instanceof GetterFieldImpl
			);

			assertTrue(
					"Property access should be used.",
					tuplizer.getGetter( 0 ) instanceof GetterMethodImpl
			);
		}
		finally {
			factory.close();
		}
	}
