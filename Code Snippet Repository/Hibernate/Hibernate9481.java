	@Test
	@SuppressWarnings("")
	public void testInParametersNullnessPassingInNamedQueriesViaHints() {
		Session session = openSession();
		session.beginTransaction();

		// similar to #testInParametersNotSet and #testInParametersNotSetPass in terms of testing
		// support for specifying whether to pass NULL argument values or not.  This version tests
		// named procedure support via hints.

		// first a fixture - this execution should fail
		{
			ProcedureCall query = session.getNamedProcedureCall( "findUserRangeNoNullPassing" );
			query.getParameterRegistration( 2 ).bindValue( 2 );
			try {
				query.getOutputs();
				fail( "Expecting failure due to missing parameter bind" );
			}
			catch (JDBCException ignore) {
			}
		}

		// here we enable NULL passing via hint through a named parameter
		{
			ProcedureCall query = session.getNamedProcedureCall( "findUserRangeNamedNullPassing" );
			query.getParameterRegistration( "secondArg" ).bindValue( 2 );
			query.getOutputs();
		}

		// here we enable NULL passing via hint through a named parameter
		{
			ProcedureCall query = session.getNamedProcedureCall( "findUserRangeOrdinalNullPassing" );
			query.getParameterRegistration( 2 ).bindValue( 2 );
			query.getOutputs();
		}

		session.getTransaction().commit();
		session.close();
	}
