	@Test
	public void jdbc() {
		//tag::transactions-api-jdbc-example[]
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				// "jdbc" is the default, but for explicitness
				.applySetting( AvailableSettings.TRANSACTION_COORDINATOR_STRATEGY, "jdbc" )
				.build();

		Metadata metadata = new MetadataSources( serviceRegistry )
				.addAnnotatedClass( Customer.class )
				.getMetadataBuilder()
				.build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
				.build();

		Session session = sessionFactory.openSession();
		try {
			// calls Connection#setAutoCommit( false ) to
			// signal start of transaction
			session.getTransaction().begin();

			session.createQuery( "UPDATE customer set NAME = 'Sir. '||NAME" )
					.executeUpdate();

			// calls Connection#commit(), if an error
			// happens we attempt a rollback
			session.getTransaction().commit();
		}
		catch ( Exception e ) {
			// we may need to rollback depending on
			// where the exception happened
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
		//end::transactions-api-jdbc-example[]
	}
