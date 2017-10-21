	@Test
	@TestForIssue( jiraKey = "" )
	public void testIncompleteScrollSecondResultInTransaction() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		ScrollableResults results = s.createQuery( QUERY + " order by p.name asc" ).scroll();
		results.next();
		Parent p = (Parent) results.get( 0 );
		assertResultFromOneUser( p );
		results.next();
		p = (Parent) results.get( 0 );
		assertResultFromOneUser( p );
		tx.commit();
		s.close();
	}
