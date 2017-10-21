	@Test
	public void testStoredProcedureReturnValue() {
		EntityManager entityManager = createEntityManager();
		entityManager.getTransaction().begin();

		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery( "sp_phones" );
			query.registerStoredProcedureParameter( 1, Long.class, ParameterMode.IN );

			query.setParameter( 1, 1L );

			List<Object[]> personComments = query.getResultList();
			assertEquals( 2, personComments.size() );
		}
		finally {
			entityManager.getTransaction().rollback();
			entityManager.close();
		}
	}
