    @Test
    public void testFieldAnnotationPlacement() throws Exception {
        Configuration cfg = new Configuration();
        Class<?> classUnderTest = Course6.class;
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
		}
		finally {
			factory.close();
		}
	}
