	@Test
	@TestForIssue( jiraKey = "")
	public void testNegativeTwoAllocationSizeNoopOptimizer() {
		ServiceRegistryImplementor serviceRegistry = null;
		SessionFactoryImplementor sessionFactory = null;
		Session session = null;
		try {
			serviceRegistry = (ServiceRegistryImplementor) new StandardServiceRegistryBuilder()
					.applySetting( AvailableSettings.HBM2DDL_AUTO, "create-drop" )
					.build();

			Triggerable triggerable = logInspection.watchForLogMessages( "HHH000116" );

			Metadata metadata = new MetadataSources( serviceRegistry )
					.addAnnotatedClass( NegativeTwoIncrementSize.class )
					.buildMetadata();

			// NegativeTwoIncrementSize ID has allocationSize == -2, so warning should be triggered.
			assertEquals( true, triggerable.wasTriggered() );

			sessionFactory = (SessionFactoryImplementor) metadata.buildSessionFactory();

			assertOptimizer( sessionFactory, NegativeTwoIncrementSize.class, NoopOptimizer.class );

			session = sessionFactory.openSession();
			session.getTransaction().begin();

			// initial value is -10; sequence should be decremented by 1
			// (since negative NoopOptimizer negative default is -1)
			for ( Integer i = -10; i >= -15; i-- ) {
				NegativeTwoIncrementSize theEntity = new NegativeTwoIncrementSize();
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
