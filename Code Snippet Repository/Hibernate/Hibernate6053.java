	@Test
	public void testNamedStoredProcedureExecution() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery( "User.findByName" );
			query.setParameter( "USER_NAME_PARAM", "my_name" );

			query.getResultList();
		}
		finally {
			em.close();
		}
	}
