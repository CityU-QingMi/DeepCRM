	@Test
	public void testStoreProcedureGetParameterByPosition() {
		final EntityManager entityManager = getOrCreateEntityManager();
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery( "by_Id", MyEntity.class );
			query.registerStoredProcedureParameter( 1, Long.class, ParameterMode.IN );

			query.setParameter( 1, 1L );

			final List resultList = query.getResultList();
			assertThat( resultList.size(), is( 1 ) );

			final Set<Parameter<?>> parameters = query.getParameters();
			assertThat( parameters.size(), is( 1 ) );

			final Parameter<?> parameter = query.getParameter( 1 );
			assertThat( parameter, not( nullValue() ) );

			try {
				query.getParameter( 2 );
				fail( "IllegalArgumentException expected, parameter at position 2 does not exist" );
			}
			catch (IllegalArgumentException iae) {
				//expected
			}
		}
		finally {
			entityManager.close();
		}
	}
