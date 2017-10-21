	@Test
	public void testRollbackExceptionOnOptimisticLockException() throws Exception {
		Book book = new Book();
		book.name = "Stolen keys";
		book.id = null; //new Integer( 50 );
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( book );
		em.flush();
		em.clear();
		book.setName( "kitty kid" );
		em.merge( book );
		em.flush();
		em.clear();
		book.setName( "kitty kid2" ); //non updated version
		em.unwrap( Session.class ).update( book );
		try {
			em.getTransaction().commit();
			fail( "Commit should be rollbacked" );
		}
		catch ( RollbackException e ) {
			assertTrue(
					"During flush a StateStateException is wrapped into a OptimisticLockException",
					e.getCause() instanceof OptimisticLockException
			);
		}
		finally {
			em.close();
		}

	}
