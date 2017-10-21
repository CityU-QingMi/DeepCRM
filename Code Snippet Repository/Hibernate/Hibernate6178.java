	@Test
	public void testRollbackOnlyOnPersistenceException() throws Exception {
		Book book = new Book();
		book.name = "Stolen keys";
		book.id = null; //new Integer( 50 );
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			em.persist( book );
			em.flush();
			em.clear();
			book.setName( "kitty kid" );
			em.merge( book );
			em.flush();
			em.clear();
			book.setName( "kitty kid2" ); //non updated version
			em.merge( book );
			em.flush();
			fail( "optimistic locking exception" );
		}
		catch ( PersistenceException e ) {
			//success
		}
		try {
			em.getTransaction().commit();
			fail( "Commit should be rollbacked" );
		}
		catch ( RollbackException e ) {
			//success
		}
		finally {
			em.close();
		}
	}
