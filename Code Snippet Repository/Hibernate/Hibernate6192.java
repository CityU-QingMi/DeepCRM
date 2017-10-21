	@Test
	@TestForIssue( jiraKey = "")
	public void checkRollBackTransactionIsExecutedOnceWhenACommitFails() throws Exception {
		EntityManager em = createEntityManager();
		try {
			final Session session = em.unwrap( Session.class );
			final OperationCollectorObserver transactionObserver = new OperationCollectorObserver();
			( (JdbcSessionOwner) session ).getTransactionCoordinator().addObserver( transactionObserver );
			em.getTransaction().begin();

			// given two inserted records
			em.persist( new Shipment( "shipment-1", "INITIAL" ) );
			em.persist( new Shipment( "shipment-2", "INITIAL" ) );

			em.flush();
			em.clear();

			try {
				// when provoking a duplicate-key exception
				em.persist( new Shipment( "shipment-1", "INITIAL" ) );
				em.getTransaction().commit();
				fail( "Expected exception was not raised" );
			}
			catch (Exception e) {
				// Nothing to do
			}

			assertThat( transactionObserver.getUnSuccessfulAfterCompletion(), is( 1 ) );

			em.clear();
			em.getTransaction().begin();

			Shipment shipment = em.find( Shipment.class, "shipment-1" );
			if ( shipment != null ) {
				em.remove( shipment );
			}

			shipment = em.find( Shipment.class, "shipment-2" );
			if ( shipment != null ) {
				em.remove( shipment );
			}

			em.getTransaction().commit();
		}
		finally {
			em.close();
		}
	}
