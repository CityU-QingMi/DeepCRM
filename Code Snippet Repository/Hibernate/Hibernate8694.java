	@Test
	public void testAnotherBasicCollection() {
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass( Boy.class );
		cfg.addAnnotatedClass( Country.class );
		cfg.addAnnotatedClass( TestCourse.class );
		cfg.addAnnotatedClass( Matrix.class );
		SessionFactoryImplementor sf = (SessionFactoryImplementor) cfg.buildSessionFactory();

		try {
			doCompare( sf, (OuterJoinLoadable) sf.getClassMetadata( Boy.class ) );
		}
		finally {
			sf.close();
		}
	}
