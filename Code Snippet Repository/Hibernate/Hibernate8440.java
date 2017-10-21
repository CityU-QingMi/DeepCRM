	@Test
	@TestForIssue( jiraKey = "" )
	public void testFilteringJoinedSubclasses() {
		Session s = openSession();
		s.beginTransaction();
		JoinedEntity root = new JoinedEntity( 1, "root" );
		s.save( root );
		JoinedEntitySubclass child = new JoinedEntitySubclass( 2, "child", root );
		s.save( child );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();

		// in the select clause which causes an implicit inclusion of subclass joins, the test here makes sure that
		// the TREAT-AS effects the join-type used.
		List result = s.createQuery( "select e from JoinedEntity e" ).list();
		assertEquals( 2, result.size() );
		result = s.createQuery( "select treat (e as JoinedEntitySubclass) from JoinedEntity e" ).list();
		assertEquals( 1, result.size() );
		result = s.createQuery( "select treat (e as JoinedEntitySubSubclass) from JoinedEntity e" ).list();
		assertEquals( 0, result.size() );

		// in join
		result = s.createQuery( "from JoinedEntity e inner join e.other" ).list();
		assertEquals( 1, result.size() );
		result = s.createQuery( "from JoinedEntity e inner join treat (e.other as JoinedEntitySubclass)" ).list();
		assertEquals( 0, result.size() );
		result = s.createQuery( "from JoinedEntity e inner join treat (e.other as JoinedEntitySubSubclass)" ).list();
		assertEquals( 0, result.size() );

		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( child );
		s.delete( root );
		s.getTransaction().commit();
		s.close();
	}
