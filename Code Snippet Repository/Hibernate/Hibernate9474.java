	@Test
	public void baseTest() {
		Session session = openSession();
		session.beginTransaction();

		ProcedureCall procedureCall = session.createStoredProcedureCall( "user");
		ProcedureOutputs procedureOutputs = procedureCall.getOutputs();
		Output currentOutput = procedureOutputs.getCurrent();
		assertNotNull( currentOutput );
		ResultSetOutput resultSetReturn = assertTyping( ResultSetOutput.class, currentOutput );
		String name = (String) resultSetReturn.getSingleResult();
		assertEquals( "SA", name );

		session.getTransaction().commit();
		session.close();
	}
