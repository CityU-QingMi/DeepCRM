    @Test
    public void testExplicitPropertyAccessAnnotationsOnProperty() throws Exception {
        Configuration cfg = new Configuration();
        Class<?> classUnderTest = Course2.class;
        cfg.addAnnotatedClass( classUnderTest );
        cfg.addAnnotatedClass( Student.class );
        SessionFactoryImplementor factory = (SessionFactoryImplementor) cfg.buildSessionFactory( serviceRegistry );
		try {
			EntityTuplizer tuplizer = factory.getEntityPersister( classUnderTest.getName() )
					.getEntityMetamodel()
					.getTuplizer();
			assertTrue(
					"Property access should be used.",
					tuplizer.getIdentifierGetter() instanceof GetterMethodImpl
			);
		}
		finally {
			factory.close();
		}
	}
