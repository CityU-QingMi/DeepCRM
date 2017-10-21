	@Test
	@TestForIssue(jiraKey = "")
	public void testMultiThreadTransactionTimeout() throws Exception {
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();

		EntityManager em = entityManagerFactory().createEntityManager();
		final SessionImpl sImpl = em.unwrap( SessionImpl.class );

		final CountDownLatch latch = new CountDownLatch( 1 );

		Thread thread = new Thread() {
			public void run() {
				((JtaTransactionCoordinatorImpl)sImpl.getTransactionCoordinator()).getSynchronizationCallbackCoordinator()
						.afterCompletion( Status.STATUS_ROLLEDBACK );
				latch.countDown();
			}
		};
		thread.start();

		latch.await();

		boolean caught = false;
		try {
			em.persist( new Book( "The Book of Foo", 1 ) );
		}
		catch ( PersistenceException e ) {
			caught = e.getCause().getClass().equals( HibernateException.class );
		}
		assertTrue( caught );

		// Ensure that the connection was closed by the background thread.
		caught = false;
		try {
			em.createQuery( "from Book" ).getResultList();
		}
		catch ( PersistenceException e ) {
			// HHH-9312
			caught = true;
		}catch (Exception e){
			caught = true;
		}
		assertTrue( caught );

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().rollback();
		em.close();
	}
