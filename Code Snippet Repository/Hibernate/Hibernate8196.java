	@Test
	@TestForIssue( jiraKey = "" )
	public void testScrollOrderParentAscChildrenDesc() {
		Session s = openSession();
		s.beginTransaction();
		ScrollableResults results = s.createQuery( QUERY + " order by p.name asc, c.name desc" ).scroll();
		List list = new ArrayList();
		while ( results.next() ) {
			list.add( results.get( 0 ) );
		}
		assertResultFromAllUsers( list );
		s.getTransaction().commit();
		s.close();
	}
