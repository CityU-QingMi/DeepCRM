	@Test
	@TestForIssue( jiraKey = "")
	public void testMergeWithDeletionOrphanRemovalThenCloseWithAnActiveTransaction() throws Exception {
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		EntityManager em = getOrCreateEntityManager();
		try {
			Muffin muffin = new Muffin();
			muffin.setKind( "blueberry" );
			SmallBox box = new SmallBox(muffin);
			box.setColor( "red-and-white" );
			em.persist( box );
			em.close();
			TestingJtaPlatformImpl.INSTANCE.getTransactionManager().commit();

			TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
			em = getOrCreateEntityManager();

			box.emptyBox();

			em.merge( box );

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
			final List<SmallBox> boxes = em.createQuery( "from SmallBox" ).getResultList();
			assertThat( boxes.size(), is( 1 ) );
			assertTrue( boxes.get( 0 ).isEmpty());
		}
		finally {
			em.close();
		}
	}
