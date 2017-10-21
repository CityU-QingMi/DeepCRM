	@Test
	public void testSettingNonExistingParams() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();

		try {
			// non-existing positional param
			try {
				StoredProcedureQuery query = em.createNamedStoredProcedureQuery( "positional-param" );
				query.setParameter( 99, 1 );
				fail( "Expecting an exception" );
			}
			catch (IllegalArgumentException expected) {
				// this is the expected condition
			}

			// non-existing named param
			try {
				StoredProcedureQuery query = em.createNamedStoredProcedureQuery( "positional-param" );
				query.setParameter( "does-not-exist", 1 );
				fail( "Expecting an exception" );
			}
			catch (IllegalArgumentException expected) {
				// this is the expected condition
			}
		}
		finally {
			em.getTransaction().commit();
			em.close();
		}
	}
