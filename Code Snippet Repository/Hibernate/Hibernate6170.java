	@Test
	@TestForIssue(jiraKey = "")
	public void testCommitReleasesLogicalConnection() throws Exception {
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		EntityManager em = getOrCreateEntityManager();
		try {
			Box box = new Box();
			box.setColor( "red-and-white" );
			em.persist( box );
			final SessionImpl session = (SessionImpl) em.unwrap( Session.class );
			final JdbcCoordinatorImpl jdbcCoordinator = (JdbcCoordinatorImpl) session.getJdbcCoordinator();
			em.close();
			TestingJtaPlatformImpl.INSTANCE.getTransactionManager().commit();
			assertThat(
					"The logical connection is still open after commit",
					jdbcCoordinator.getLogicalConnection().isOpen(),
					is( false )
			);
		}
		catch (Exception e) {
			final TransactionManager transactionManager = TestingJtaPlatformImpl.INSTANCE.getTransactionManager();
			if ( transactionManager.getTransaction() != null && transactionManager.getTransaction()
					.getStatus() == Status.STATUS_ACTIVE ) {
				TestingJtaPlatformImpl.INSTANCE.getTransactionManager().rollback();
			}
			throw e;
		}
		finally {
			if ( em.isOpen() ) {
				em.close();
			}
		}
	}
