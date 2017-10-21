	@Test
	@TestForIssue(jiraKey = "")
	public void testGetPositionalParameters2() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery( "User.inoutprocpositional" );
			final Set<Parameter<?>> parameters = query.getParameters();
			assertThat( parameters.size(), is( 2 ) );
			assertThat( query.getParameter( 1 ), not( nullValue() ) );
			assertThat( query.getParameter( 2 ), not( nullValue() ) );
			assertThat( query.getParameter( 1, Integer.class ), not( nullValue() ) );
			try {
				query.getParameter( 3 );
				fail( "An IllegalArgumentException is expected, A parameter at position 3 does not exist" );
			}
			catch (IllegalArgumentException iae) {
				//expected
			}

			try {
				query.getParameter( 1, String.class );
				fail( "An IllegalArgumentException is expected, The parameter at position 1 is of type Integer not String" );
			}
			catch (IllegalArgumentException iae) {
				//expected
			}

		}
		finally {
			em.close();
		}
	}
