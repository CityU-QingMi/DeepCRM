	@Test
	public void testHibernateProcedureCallOutParameter() {

		EntityManager entityManager = createEntityManager();
		entityManager.getTransaction().begin();

		try {
			Session session = entityManager.unwrap( Session.class );

			ProcedureCall call = session.createStoredProcedureCall( "sp_count_phones" );
			call.registerParameter( "personId", Long.class, ParameterMode.IN ).bindValue( 1L );
			call.registerParameter( "phoneCount", Long.class, ParameterMode.OUT );

			Long phoneCount = (Long) call.getOutputs().getOutputParameterValue( "phoneCount" );
			assertEquals( Long.valueOf( 2 ), phoneCount );
		}
		finally {
			entityManager.getTransaction().rollback();
			entityManager.close();
		}
	}
