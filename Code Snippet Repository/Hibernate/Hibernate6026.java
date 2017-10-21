	@Test
	public void testStoreProcedureGetParameters() {
		final EntityManager entityManager = getOrCreateEntityManager();
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery( "get_all_entities", MyEntity.class );
			final Set<Parameter<?>> parameters = query.getParameters();
			assertThat( parameters.size(), is( 0 ) );

			final List resultList = query.getResultList();
			assertThat( resultList.size(), is( 1 ) );
		}
		finally {
			entityManager.close();
		}
	}
