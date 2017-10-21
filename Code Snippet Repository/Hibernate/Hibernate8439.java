	@Test
	@TestForIssue( jiraKey = "" )
	public void testFilteringDiscriminatorSubclasses() {
		Session s = openSession();
		s.beginTransaction();
		DiscriminatorEntity root = new DiscriminatorEntity( 1, "root" );
		s.save( root );
		DiscriminatorEntitySubclass child = new DiscriminatorEntitySubclass( 2, "child", root );
		s.save( child );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();

		// in select clause
		List result = s.createQuery( "select e from DiscriminatorEntity e" ).list();
		assertEquals( 2, result.size() );
		result = s.createQuery( "select treat (e as DiscriminatorEntitySubclass) from DiscriminatorEntity e" ).list();
		assertEquals( 1, result.size() );
		result = s.createQuery( "select treat (e as DiscriminatorEntitySubSubclass) from DiscriminatorEntity e" ).list();
		assertEquals( 0, result.size() );

		// in join
		result = s.createQuery( "from DiscriminatorEntity e inner join e.other" ).list();
		assertEquals( 1, result.size() );
		result = s.createQuery( "from DiscriminatorEntity e inner join treat (e.other as DiscriminatorEntitySubclass)" ).list();
		assertEquals( 0, result.size() );
		result = s.createQuery( "from DiscriminatorEntity e inner join treat (e.other as DiscriminatorEntitySubSubclass)" ).list();
		assertEquals( 0, result.size() );

		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( root );
		s.delete( child );
		s.getTransaction().commit();
		s.close();
	}
