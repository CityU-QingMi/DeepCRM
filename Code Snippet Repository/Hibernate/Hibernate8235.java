	@Test
	@TestForIssue( jiraKey = "" )
	public void testPositiveTwoAllocationSizeNoopOptimizer() {
		ServiceRegistryImplementor serviceRegistry = null;
		SessionFactoryImplementor sessionFactory = null;
		Session session = null;
		try {
			serviceRegistry = (ServiceRegistryImplementor) new StandardServiceRegistryBuilder()
					.applySetting( AvailableSettings.PREFERRED_POOLED_OPTIMIZER, "none" )
					.applySetting( AvailableSettings.HBM2DDL_AUTO, "create-drop" )
					.build();

			Triggerable triggerable = logInspection.watchForLogMessages( "HHH000116" );

			Metadata metadata = new MetadataSources( serviceRegistry )
					.addAnnotatedClass( PositiveTwoIncrementSize.class )
					.buildMetadata();

			// NoopOptimizer is preferred (due to setting AvailableSettings.PREFERRED_POOLED_OPTIMIZER to "false")
			// PositiveTwoIncrementSize ID has allocationSize == 2, so warning should be triggered.
			assertEquals( true, triggerable.wasTriggered() );

			sessionFactory = (SessionFactoryImplementor) metadata.buildSessionFactory();

			assertOptimizer( sessionFactory, PositiveTwoIncrementSize.class, NoopOptimizer.class );

			session = sessionFactory.openSession();
			session.getTransaction().begin();

			// initial value is -5; sequence should be incremented by 1
			// (since NoopOptimizer positive default allocationSize is 1)
			for ( Integer i = -5; i <= 5; i++ ) {
				PositiveTwoIncrementSize theEntity = new PositiveTwoIncrementSize();
				session.persist( theEntity );
				assertEquals( i, theEntity.id );
			}
		}
		finally {
			if ( session != null ) {
				session.getTransaction().rollback();
				session.close();
			}
			if ( sessionFactory != null ) {
				sessionFactory.close();
			}
			if ( serviceRegistry != null ) {
				serviceRegistry.destroy();
			}
		}
	}
