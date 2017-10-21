	@Test
	@TestForIssue(jiraKey = "")
	public void testNativeQueryOrdinalParameter() {
		EntityManager em = getOrCreateEntityManager();
		try {
			Query query = em.createNativeQuery( "SELECT * FROM GAME g WHERE title = ?" );
			query.setParameter( 1, "Super Mario Brothers" );
			List list = query.getResultList();
			assertEquals( 1, list.size() );
		}
		finally {
			em.close();
		}
	}
