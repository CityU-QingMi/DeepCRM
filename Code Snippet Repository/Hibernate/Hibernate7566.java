	@Test
	@TestForIssue(jiraKey = "")
	public void testAnEmptyListIsReturnedWhenSetMaxResultsToZero() {
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			final Criteria crit = session.createCriteria( Person.class );
			crit.setMaxResults( 0 );
			final List list = crit.list();
			assertTrue( "The list should be empty with setMaxResults 0", list.isEmpty() );
		} );
	}
