	@Test
	public void testExplicitClassReturn() {
		Session session = sf.openSession();
		session.beginTransaction();

		ProcedureCall call = session.createStoredProcedureCall( "all_items", Item.class );
		call.registerParameter( 1, void.class, ParameterMode.REF_CURSOR );
		ProcedureOutputs outputs = call.getOutputs();
		ResultSetOutput results = assertTyping( ResultSetOutput.class, outputs.getCurrent() );

		session.getTransaction().commit();
		session.close();
	}
