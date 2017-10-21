	@Test
	@TestForIssue( jiraKey = "" )
	public void testImplicitJoinsInDifferentClauses() {
		// both the classic and ast translators output the same syntactically valid sql
		// for all of these cases; the issue is that shallow (iterate) and
		// non-shallow (list/scroll) queries return different results because the
		// shallow skips the inner join which "weeds out" results from the non-shallow queries.
		// The results were initially different depending upon the clause(s) in which the
		// implicit join occurred
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

		checkCounts( "select e.owner from SimpleAssociatedEntity e", 1, "implicit-join in select clause" );
		checkCounts( "select e.id, e.owner from SimpleAssociatedEntity e", 1, "implicit-join in select clause" );

		// resolved to a "id short cut" when part of the order by clause -> no inner join = no weeding out...
		checkCounts( "from SimpleAssociatedEntity e order by e.owner", 2, "implicit-join in order-by clause" );
		// resolved to a "id short cut" when part of the group by clause -> no inner join = no weeding out...
		checkCounts( "select e.owner.id, count(*) from SimpleAssociatedEntity e group by e.owner", 2, "implicit-join in select and group-by clauses" );

	 	s = openSession();
		s.beginTransaction();
		s.delete( e1 );
		s.delete( e2 );
		s.delete( owner );
		s.getTransaction().commit();
		s.close();
	}
