	@Test
	public void testMultipleGetUpdateCountCalls() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery( "findOneUser" );
			// this is what the TCK attempts to do, don't shoot the messenger...
			query.getUpdateCount();
			// yep, twice
			query.getUpdateCount();
		}
		finally {
			em.getTransaction().commit();
			em.close();
		}
	}
