	@Test
	@TestForIssue(jiraKey = "")
	public void testGetPositionalParameters() {
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery( "User.inoutproc" );
			final Set<Parameter<?>> parameters = query.getParameters();
			assertThat( parameters.size(), is( 2 ) );
			try {
				query.getParameter( 1 );
				fail( "An IllegalArgumentException is expected, The stored procedure has named parameters not positional" );
			}
			catch (IllegalArgumentException iae) {
				//expected
			}
			try {
				query.getParameter( 1, String.class );
				fail( "An IllegalArgumentException is expected, The stored procedure has named parameters not positional" );
			}
			catch (IllegalArgumentException iae) {
				//expected
			}
		}
		finally {
			em.close();
		}
	}
