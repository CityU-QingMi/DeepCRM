	@Test
	public void testCreateException() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Node dupe = new Node( "dupe" );
		em.persist( dupe );
		em.persist( dupe );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( dupe );
		try {
			em.getTransaction().commit();
			fail( "Cannot persist() twice the same entity" );
		}
		catch ( Exception cve ) {
			//verify that an exception is thrown!
		}
		em.close();

		Node nondupe = new Node( "nondupe" );
		nondupe.addChild( dupe );

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( nondupe );
		try {
			em.getTransaction().commit();
			assertFalse( true );
		}
		catch ( RollbackException e ) {
			//verify that an exception is thrown!
		}
		em.close();
	}
