	@Test
	public void testCommitOccurs() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.joinTransaction();
		doInstantiateAndSave(em);

		setComplete();
		endTransaction();	// Should rollback
		assertEquals("Tx must have committed back", 1, countRowsInTable(em, "person"));

		// Now clean up the database
		deleteFromTables("person");
	}
