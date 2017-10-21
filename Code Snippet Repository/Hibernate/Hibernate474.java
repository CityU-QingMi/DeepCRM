	@Test
	public void cmt() {
		//tag::transactions-api-cmt-example[]
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				// "jdbc" is the default, but for explicitness
				.applySetting( AvailableSettings.TRANSACTION_COORDINATOR_STRATEGY, "jta" )
				.build();

		Metadata metadata = new MetadataSources( serviceRegistry )
				.addAnnotatedClass( Customer.class )
				.getMetadataBuilder()
				.build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
				.build();

		// Note: depending on the JtaPlatform used and some optional settings,
		// the underlying transactions here will be controlled through either
		// the JTA TransactionManager or UserTransaction

		Session session = sessionFactory.openSession();
		try {
			// Since we are in CMT, a JTA transaction would
			// already have been started.  This call essentially
			// no-ops
			session.getTransaction().begin();

			Number customerCount = (Number) session.createQuery( "select count(c) from Customer c" ).uniqueResult();

			// Since we did not start the transaction ( CMT ),
			// we also will not end it.  This call essentially
			// no-ops in terms of transaction handling.
			session.getTransaction().commit();
		}
		catch ( Exception e ) {
			// again, the rollback call here would no-op (aside from
			// marking the underlying CMT transaction for rollback only).
			if ( session.getTransaction().getStatus() == TransactionStatus.ACTIVE
					|| session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK ) {
				session.getTransaction().rollback();
			}
			// handle the underlying error
		}
		finally {
			session.close();
			sessionFactory.close();
		}
		//end::transactions-api-cmt-example[]
	}
