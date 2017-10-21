	@Test
	@TestForIssue( jiraKey = "" )
	public void testNegativeTwoAllocationSizePositiveStartNoopOptimizer() {
		ServiceRegistryImplementor serviceRegistry = null;
		SessionFactoryImplementor sessionFactory = null;
		Session session = null;
		try {
			serviceRegistry = (ServiceRegistryImplementor) new StandardServiceRegistryBuilder()
					.applySetting( AvailableSettings.HBM2DDL_AUTO, "create-drop" )
					.build();

			Triggerable triggerable = logInspection.watchForLogMessages( "HHH000116" );

			Metadata metadata = new MetadataSources( serviceRegistry )
					.addAnnotatedClass( NegativeTwoIncrementSizePositiveInitialValue.class )
					.buildMetadata();

			// NegativeTwoIncrementSizePositiveInitialValue ID has allocationSize == -2, so warning should be triggered.
			assertEquals( true, triggerable.wasTriggered() );

			sessionFactory = (SessionFactoryImplementor) metadata.buildSessionFactory();

			assertOptimizer( sessionFactory, NegativeTwoIncrementSizePositiveInitialValue.class, NoopOptimizer.class );

			session = sessionFactory.openSession();
			session.getTransaction().begin();

			// initial value is 5; sequence should be decremented by 1
			// (since negative NoopOptimizer negative default is -1)
			for ( Integer i = 5; i <= -5; i-- ) {
				NegativeTwoIncrementSizePositiveInitialValue theEntity =
						new NegativeTwoIncrementSizePositiveInitialValue();
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
