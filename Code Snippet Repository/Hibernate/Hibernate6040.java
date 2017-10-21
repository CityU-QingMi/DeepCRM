	@Test
	@FailureExpected( jiraKey = "", message = "" )
	public void testHasMoreResultsHandlingTckChallenge() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery( "findOneUser", User.class );
			assertTrue( query.execute() );
			assertTrue( query.hasMoreResults() );
			query.getResultList();
			assertFalse( query.hasMoreResults() );
		}
		finally {
			em.getTransaction().commit();
			em.close();
		}
	}
