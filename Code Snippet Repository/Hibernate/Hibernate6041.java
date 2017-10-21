	@Test
	public void testHasMoreResultsHandling() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery( "findOneUser", User.class );
			assertTrue( query.execute() );
			query.getResultList();
			assertFalse( query.hasMoreResults() );
		}
		finally {
			em.getTransaction().commit();
			em.close();
		}
	}
