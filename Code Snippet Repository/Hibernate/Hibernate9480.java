	@Test
	public void testInParametersNotSetPass() {
		Session session = openSession();
		session.beginTransaction();

		// unlike #testInParametersNotSet here we are asking that the NULL be passed
		// so these executions should succeed


		ProcedureCall query = session.createStoredProcedureCall( "findUserRange" );
		query.registerParameter( 1, Integer.class, ParameterMode.IN ).enablePassingNulls( true );
		query.registerParameter( 2, Integer.class, ParameterMode.IN ).bindValue( 2 );
		query.getOutputs();

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
