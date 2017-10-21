	@Test
	@TestForIssue(jiraKey = "")
	public void testWithFetchJoin() {
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			final List<String> stateCodes = Arrays.asList( "DC", "CT" );
			final Criteria crit = session.createCriteria( Person.class );
			crit.createCriteria( "states" ).add( Restrictions.in( "code", stateCodes ) );
			crit.setMaxResults( 10 );
			crit.list();
		} );
	}
