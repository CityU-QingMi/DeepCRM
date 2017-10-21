	@Test
	public void testSettingInParamDefinedOnNamedStoredProcedureQuery() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		try {
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery( "positional-param" );
			query.setParameter( 1, 1 );
		}
		finally {
			em.getTransaction().commit();
			em.close();
		}
	}
