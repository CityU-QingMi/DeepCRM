	@Test
	@TestForIssue( jiraKey = "")
	public void testNegativeOneAllocationSizeNoopOptimizer() {
		ServiceRegistryImplementor serviceRegistry = null;
		SessionFactoryImplementor sessionFactory = null;
		Session session = null;
		try {
			serviceRegistry = (ServiceRegistryImplementor) new StandardServiceRegistryBuilder()
					.applySetting( AvailableSettings.HBM2DDL_AUTO, "create-drop" )
					.build();

			Triggerable triggerable = logInspection.watchForLogMessages( "HHH000116" );

			Metadata metadata = new MetadataSources( serviceRegistry )
					.addAnnotatedClass( NegativeOneIncrementSize.class )
					.buildMetadata();

			// NegativeOneIncrementSize ID has allocationSize == -1, so warning should not be triggered.
			assertEquals( false, triggerable.wasTriggered() );

			sessionFactory = (SessionFactoryImplementor) metadata.buildSessionFactory();

			assertOptimizer( sessionFactory, NegativeOneIncrementSize.class, NoopOptimizer.class );

			session = sessionFactory.openSession();
			session.getTransaction().begin();

			// initial value is -10; sequence should be decremented by 1 (since allocationSize is -1)
			for ( Integer i = -10; i >= -15; i-- ) {
				NegativeOneIncrementSize theEntity = new NegativeOneIncrementSize();
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
