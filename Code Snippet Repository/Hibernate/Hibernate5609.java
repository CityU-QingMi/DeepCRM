	@Test
	public void testNoAssignments() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		try {
			CriteriaUpdate<Customer> updateCriteria = builder.createCriteriaUpdate( Customer.class );
			updateCriteria.from( Customer.class );
			em.createQuery( updateCriteria ).executeUpdate();
			fail( "Expecting failure due to no assignments" );
		}
		catch (IllegalArgumentException iae) {
			// expected
		}

		// changed to rollback since HHH-8442 causes transaction to be marked for rollback only
		assertTrue( em.getTransaction().getRollbackOnly() );
		em.getTransaction().rollback();
		em.close();
	}
