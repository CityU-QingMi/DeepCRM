	@Test
	public void testCreateExceptionWithGeneratedId() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		NumberedNode dupe = new NumberedNode( "dupe" );
		em.persist( dupe );
		em.persist( dupe );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			em.persist( dupe );
			fail();
		}
		catch ( PersistenceException poe ) {
			//verify that an exception is thrown!
		}
		em.getTransaction().rollback();
		em.close();

		NumberedNode nondupe = new NumberedNode( "nondupe" );
		nondupe.addChild( dupe );

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			em.persist( nondupe );
			fail();
		}
		catch ( PersistenceException poe ) {
			//verify that an exception is thrown!
		}
		em.getTransaction().rollback();
		em.close();
	}
