	@Test
	public void testEntityComposite() {
		final SessionFactory sf = new Configuration()
				.addAnnotatedClass( TestCourse.class )
				.buildSessionFactory();
		try {
			final EntityPersister ep = (EntityPersister) sf.getClassMetadata( TestCourse.class );
			MetamodelGraphWalker.visitEntity( new LoggingAssociationVisitationStrategy(), ep );
		}
		finally {
			sf.close();
		}
	}
