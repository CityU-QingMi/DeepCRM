	@Test
	@TestForIssue(jiraKey = "")
	public void testPersistThenCloseWithAnActiveTransaction() throws Exception {
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		EntityManager em = getOrCreateEntityManager();
		try {
			Box box = new Box();
			box.setColor( "red-and-white" );
			em.persist( box );
			em.close();
			TestingJtaPlatformImpl.INSTANCE.getTransactionManager().commit();
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
		em = getOrCreateEntityManager();
		try {
			final List results = em.createQuery( "from Box" ).getResultList();
			assertThat( results.size(), is( 1 ) );
		}
		finally {
			em.close();
		}
	}
