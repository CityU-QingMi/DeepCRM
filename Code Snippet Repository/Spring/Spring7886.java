	@Test
	public void testCannotFlushWithoutGettingTransaction() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			doInstantiateAndSave(em);
			fail("Should have thrown TransactionRequiredException");
		}
		catch (TransactionRequiredException ex) {
			// expected
		}

		// TODO following lines are a workaround for Hibernate bug
		// If Hibernate throws an exception due to flush(),
		// it actually HAS flushed, meaning that the database
		// was updated outside the transaction
		deleteAllPeopleUsingEntityManager(sharedEntityManager);
		setComplete();
	}
