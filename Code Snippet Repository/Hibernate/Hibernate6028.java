	@Test
	@TestForIssue(jiraKey = "")
	public void testNamedStoredProcedureExecution() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery( "User.inoutproc" );
			query.setParameter( "arg1", 1 );
			query.execute();
		}
		finally {
			em.close();
		}
	}
