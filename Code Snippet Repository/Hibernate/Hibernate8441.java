	@Test
	@TestForIssue( jiraKey = "" )
	@FailureExpected( jiraKey = "" )
	public void testRestrictionsOnJoinedSubclasses() {
		Session s = openSession();
		s.beginTransaction();
		JoinedEntity root = new JoinedEntity( 1, "root" );
		s.save( root );
		JoinedEntitySubclass child1 = new JoinedEntitySubclass( 2, "child1", root );
		s.save( child1 );
		JoinedEntitySubclass2 child2 = new JoinedEntitySubclass2( 3, "child2", root );
		s.save( child2 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();

		List result = s.createQuery( "select e from JoinedEntity e where treat (e as JoinedEntitySubclass ).name = 'child1'" ).list();
		assertEquals( 1, result.size() );
		assertTrue( JoinedEntitySubclass.class.isInstance( result.get( 0 ) ) );

		result = s.createQuery( "select e from JoinedEntity e where treat (e as JoinedEntitySubclass2 ).name = 'child1'" ).list();
		assertEquals( 0, result.size() );

		result = s.createQuery( "select e from JoinedEntity e where treat (e as JoinedEntitySubclass2 ).name = 'child2'" ).list();
		assertEquals( 1, result.size() );
		assertTrue( JoinedEntitySubclass2.class.isInstance( result.get( 0 ) ) );

		result = s.createQuery( "select e from JoinedEntity e where treat (e as JoinedEntitySubclass ).name = 'child1' or treat (e as JoinedEntitySubclass2 ).name = 'child2'" ).list();
		assertEquals( 2, result.size() );

		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( child1 );
		s.delete( child2 );
		s.delete( root );
		s.getTransaction().commit();
		s.close();
	}
