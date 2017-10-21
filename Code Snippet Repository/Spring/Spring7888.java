	@Test
	public void testReuseInNewTransaction() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.joinTransaction();

		doInstantiateAndSave(em);
		endTransaction();

		assertFalse(em.getTransaction().isActive());

		startNewTransaction();
		// Call any method: should cause automatic tx invocation
		assertFalse(em.contains(new Person()));

		assertFalse(em.getTransaction().isActive());
		em.joinTransaction();

		assertTrue(em.getTransaction().isActive());

		doInstantiateAndSave(em);
		setComplete();
		endTransaction();	// Should rollback
		assertEquals("Tx must have committed back", 1, countRowsInTable(em, "person"));

		// Now clean up the database
		startNewTransaction();
		em.joinTransaction();
		deleteAllPeopleUsingEntityManager(em);
		assertEquals("People have been killed", 0, countRowsInTable(em, "person"));
		setComplete();
	}
