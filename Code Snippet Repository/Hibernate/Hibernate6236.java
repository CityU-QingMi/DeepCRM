	@Test
	@TestForIssue( jiraKey = "" )
	public void testSharedTransactionContextSessionClosing() {
		Session session = sessionFactory().openSession();
		session.getTransaction().begin();

		Session secondSession = session.sessionWithOptions()
				.transactionContext()
				.openSession();
		secondSession.createCriteria( IrrelevantEntity.class ).list();

		//the list should have registered and then released a JDBC resource
		assertFalse(
				((SessionImplementor) secondSession)
						.getJdbcCoordinator()
						.getResourceRegistry()
						.hasRegisteredResources()
		);

		assertTrue( session.isOpen() );
		assertTrue( secondSession.isOpen() );

		assertSame( session.getTransaction(), secondSession.getTransaction() );

		session.getTransaction().commit();

		assertTrue( session.isOpen() );
		assertTrue( secondSession.isOpen() );

		secondSession.close();
		assertTrue( session.isOpen() );
		assertFalse( secondSession.isOpen() );

		session.close();
		assertFalse( session.isOpen() );
		assertFalse( secondSession.isOpen() );
	}
