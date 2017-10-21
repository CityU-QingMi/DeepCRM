	@Test
	public void testPartialResults() {
		Configuration cfg = new Configuration()
				.addAnnotatedClass( Employee.class )
				.setProperty( AvailableSettings.HBM2DDL_AUTO, "create-drop" );
		cfg.addAuxiliaryDatabaseObject( new ProcedureDefinition() );
		SessionFactory sf = cfg.buildSessionFactory();
		try {
			Session session = sf.openSession();
			session.beginTransaction();

			ProcedureCall call = session.createStoredProcedureCall( "allEmployeeNames", "id-fname-lname" );
			ProcedureOutputs outputs = call.getOutputs();
			ResultSetOutput output = assertTyping( ResultSetOutput.class, outputs.getCurrent() );
			assertEquals( 3, output.getResultList().size() );
			assertTyping( Employee.class, output.getResultList().get( 0 ) );

			session.getTransaction().commit();
			session.close();
		}
		finally {
			sf.close();
		}
	}
