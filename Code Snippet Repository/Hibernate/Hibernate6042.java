	@Test
	public void testResultClassHandling() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		try {
			StoredProcedureQuery query = em.createStoredProcedureQuery( "findOneUser", User.class );
			boolean isResult = query.execute();
			assertTrue( isResult );
			int updateCount = query.getUpdateCount();

			boolean results = false;
			do {
				List list = query.getResultList();
				assertEquals( 1, list.size() );
				assertTyping( User.class, list.get( 0 ) );

				results = query.hasMoreResults();
				// and it only sets the updateCount once lol
			} while ( results || updateCount != -1);
		}
		finally {
			em.getTransaction().commit();
			em.close();
		}
	}
