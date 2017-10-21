	@Test
	public void testPartialResults() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		StoredProcedureQuery query = em.createStoredProcedureQuery( "allEmployeeNames", "id-fname-lname" );
		List results = query.getResultList();
		assertEquals( 3, results.size() );
		em.getTransaction().commit();
		em.close();
	}
