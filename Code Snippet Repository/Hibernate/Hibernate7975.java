	@Test
	@TestForIssue( jiraKey = "" )
	public void testOrderByExtraParenthesis() throws Exception {
		try {
			doInHibernate( this::sessionFactory, session -> {
				session.createQuery(
					"select a from Product a " +
					"where " +
					"coalesce(a.description, :description) = :description ) " +
					"order by a.description ", Product.class)
				.setParameter( "description", "desc" )
				.getResultList();
				fail("Should have thrown exception");
			} );
		}
		catch (IllegalArgumentException e) {
			final Throwable cause = e.getCause();
			assertThat( cause, instanceOf( QuerySyntaxException.class ) );
			assertTrue( cause.getMessage().contains( "expecting EOF, found ')'" ) );
		}
	}
