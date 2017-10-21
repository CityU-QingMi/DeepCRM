	@Test
	public void testInParametersByPosition() {
		Session session = openSession();
		session.beginTransaction();

		ProcedureCall query = session.createStoredProcedureCall( "findUserRange" );
		query.registerParameter( 1, Integer.class, ParameterMode.IN ).bindValue( 1 );
		query.registerParameter( 2, Integer.class, ParameterMode.IN ).bindValue( 2 );
		ProcedureOutputs procedureResult = query.getOutputs();
		Output currentOutput = procedureResult.getCurrent();
		assertNotNull( currentOutput );
		ResultSetOutput resultSetReturn = assertTyping( ResultSetOutput.class, currentOutput );
		List results = resultSetReturn.getResultList();
		assertEquals( 1, results.size() );
		Object result = results.get( 0 );
		assertTyping( Object[].class, result );
		Integer id = (Integer) ( (Object[]) result )[0];
		String name = (String) ( (Object[]) result )[1];
		assertEquals( 1, (int) id );
		assertEquals( "User 1", name );

		session.getTransaction().commit();
		session.close();
	}
