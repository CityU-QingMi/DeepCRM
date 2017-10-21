	@Test
	@TestForIssue(jiraKey = "")
	public void testFetchWithAlias() {
		doInHibernate( this::sessionFactory, session -> {

			assertOnlyOneSelect( session.createCriteria( Cat.class, "c" )
					.setFetchMode( "c.kittens", FetchMode.JOIN )
					.setResultTransformer( CriteriaSpecification.DISTINCT_ROOT_ENTITY ) );

		} );
	}
