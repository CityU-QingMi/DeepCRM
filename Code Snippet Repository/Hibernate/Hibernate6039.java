	@Test
	public void testBasicScalarResults() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery( "findOneUser" );
			boolean isResult = query.execute();
			assertTrue( isResult );
			int updateCount = query.getUpdateCount();

			boolean results = false;
			do {
				List list = query.getResultList();
				assertEquals( 1, list.size() );

				results = query.hasMoreResults();
				// and it only sets the updateCount once lol
			} while ( results || updateCount != -1);
		}
		finally {
			em.getTransaction().commit();
			em.close();
		}
	}
