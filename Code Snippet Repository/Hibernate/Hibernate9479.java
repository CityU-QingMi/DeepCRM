	@Test
	public void testInParametersNotSet() {
		Session session = openSession();
		session.beginTransaction();

		// since the procedure does not define defaults for parameters this should result in SQLExceptions on
		// execution

		{
			ProcedureCall query = session.createStoredProcedureCall( "findUserRange" );
			query.registerParameter( 1, Integer.class, ParameterMode.IN );
			query.registerParameter( 2, Integer.class, ParameterMode.IN ).bindValue( 2 );
			try {
				query.getOutputs();
				fail( "Expecting failure due to missing parameter bind" );
			}
			catch (JDBCException expected) {
			}
		}

// H2 does not support named parameters
//		{
//			ProcedureCall query = session.createStoredProcedureCall( "findUserRange" );
//			query.registerParameter( "start", Integer.class, ParameterMode.IN );
//			query.registerParameter( "end", Integer.class, ParameterMode.IN ).bindValue( 2 );
//			try {
//				query.getOutputs();
//				fail( "Expecting failure due to missing parameter bind" );
//			}
//			catch (JDBCException expected) {
//			}
//		}

		session.getTransaction().commit();
		session.close();
	}
