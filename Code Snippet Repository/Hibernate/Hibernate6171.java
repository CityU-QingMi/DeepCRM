	@Test
	public void testAlwaysTransactionalOperations() throws Exception {
		Book book = new Book();
		book.name = "Le petit prince";
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( book );
		em.getTransaction().commit();
		try {
			em.flush();
			fail( "flush has to be inside a Tx" );
		}
		catch ( TransactionRequiredException e ) {
			//success
		}
		try {
			em.lock( book, LockModeType.READ );
			fail( "lock has to be inside a Tx" );
		}
		catch ( TransactionRequiredException e ) {
			//success
		}
		em.getTransaction().begin();
		em.remove( em.find( Book.class, book.id ) );
		em.getTransaction().commit();
		em.close();
	}
