	@Test
	public void testHibernateProcedureCallReturnValueParameter() {
		EntityManager entityManager = createEntityManager();
		entityManager.getTransaction().begin();

		try {
			Session session = entityManager.unwrap( Session.class );

			ProcedureCall call = session.createStoredProcedureCall( "sp_phones" );
			call.registerParameter( 1, Long.class, ParameterMode.IN ).bindValue( 1L );

			Output output = call.getOutputs().getCurrent();

			List<Object[]> personComments = ( (ResultSetOutput) output ).getResultList();
			assertEquals( 2, personComments.size() );
		}
		finally {
			entityManager.getTransaction().rollback();
			entityManager.close();
		}
	}
