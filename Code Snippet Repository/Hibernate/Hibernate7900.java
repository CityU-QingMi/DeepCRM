	@Test
	@TestForIssue( jiraKey = "" )
	public void testImplicitSelectEntityAssociationInShallowQuery() {
		// both the classic and ast translators output the same syntactically valid sql.
		// the issue is that shallow and non-shallow queries return different
		// results because the shallow skips the inner join which "weeds out" results
		// from the non-shallow queries...
		Session s = openSession();
		s.beginTransaction();
		SimpleEntityWithAssociation owner = new SimpleEntityWithAssociation( "owner" );
		SimpleAssociatedEntity e1 = new SimpleAssociatedEntity( "thing one", owner );
		SimpleAssociatedEntity e2 = new SimpleAssociatedEntity( "thing two" );
		s.save( e1 );
		s.save( e2 );
		s.save( owner );
		s.getTransaction().commit();
		s.close();

	 	s = openSession();
		s.beginTransaction();
		int count = determineCount( s.createQuery( "select e.id, e.owner from SimpleAssociatedEntity e" ).list().iterator() );
		assertEquals( 1, count ); // thing two would be removed from the result due to the inner join
		count = determineCount( s.createQuery( "select e.id, e.owner from SimpleAssociatedEntity e" ).iterate() );
		assertEquals( 1, count );
		s.getTransaction().commit();
		s.close();

	 	s = openSession();
		s.beginTransaction();
		s.delete( e1 );
		s.delete( e2 );
		s.delete( owner );
		s.getTransaction().commit();
		s.close();
	}
