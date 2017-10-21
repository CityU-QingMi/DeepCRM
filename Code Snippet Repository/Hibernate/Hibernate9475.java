	@Test
	public void testGetSingleResultTuple() {
		Session session = openSession();
		session.beginTransaction();

		ProcedureCall query = session.createStoredProcedureCall( "findOneUser" );
		ProcedureOutputs procedureResult = query.getOutputs();
		Output currentOutput = procedureResult.getCurrent();
		assertNotNull( currentOutput );
		ResultSetOutput resultSetReturn = assertTyping( ResultSetOutput.class, currentOutput );
		Object result = resultSetReturn.getSingleResult();
		assertTyping( Object[].class, result );
		String name = (String) ( (Object[]) result )[1];
		assertEquals( "Steve", name );

		session.getTransaction().commit();
		session.close();
	}
