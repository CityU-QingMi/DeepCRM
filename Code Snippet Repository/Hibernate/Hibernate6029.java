	@Test
	@TestForIssue(jiraKey = "")
	public void testGetNamedParameters() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery( "User.inoutproc" );
			final Set<Parameter<?>> parameters = query.getParameters();
			assertThat( parameters.size(), is( 2 ) );
			assertThat( query.getParameter( "arg1" ), not( nullValue() ) );
			assertThat( query.getParameter( "res" ), not( nullValue() ) );
			assertThat( query.getParameter( "arg1", Integer.class ), not( nullValue() ) );
			try {
				query.getParameter( "arg1", String.class );
				fail( "An IllegalArgumentException is expected, A parameter with name arg1 and type String does not exist" );
			}
			catch (IllegalArgumentException iae) {
				//expected
			}

			try {
				query.getParameter( "arg2" );
				fail( "An IllegalArgumentException is expected, A parameter with name arg2 does not exist" );
			}
			catch (IllegalArgumentException iae) {
				//expected
			}
		}
		finally {
			em.close();
		}
	}
