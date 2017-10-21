	@Test
	@FailureExpected( jiraKey = "", message = "" )
	public void testExecuteUpdate() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery( "deleteAllUsers" );
			int count = query.executeUpdate();
			// this fails because the Derby EmbeddedDriver is returning zero here rather than the actual updateCount :(
			// https://issues.apache.org/jira/browse/DERBY-211
			assertEquals( 1, count );
		}
		finally {
			em.getTransaction().commit();
			em.close();
		}
	}
