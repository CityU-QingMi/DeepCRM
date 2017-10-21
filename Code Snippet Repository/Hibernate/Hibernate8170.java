	@Test
	public void testWithClause() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction txn = s.beginTransaction();

		// one-to-many
		List list = s.createQuery( "from Human h inner join h.offspring as o with o.bodyWeight < :someLimit" )
				.setDouble( "someLimit", 1 )
				.list();
		assertTrue( "ad-hoc on did not take effect", list.isEmpty() );

		// many-to-one
		list = s.createQuery( "from Animal a inner join a.mother as m with m.bodyWeight < :someLimit" )
				.setDouble( "someLimit", 1 )
				.list();
		assertTrue( "ad-hoc on did not take effect", list.isEmpty() );

		list = s.createQuery( "from Human h inner join h.friends f with f.bodyWeight < :someLimit" )
				.setDouble( "someLimit", 25 )
				.list();
		assertTrue( "ad-hoc on did take effect", !list.isEmpty() );

		// many-to-many
		list = s.createQuery( "from Human h inner join h.friends as f with f.nickName like 'bubba'" )
				.list();
		assertTrue( "ad-hoc on did not take effect", list.isEmpty() );

		// http://opensource.atlassian.com/projects/hibernate/browse/HHH-1930
		list = s.createQuery( "from Human h inner join h.nickNames as nicknames with nicknames = 'abc'" )
				.list();
		assertTrue( "ad-hoc on did not take effect", list.isEmpty() );

		list = s.createQuery( "from Human h inner join h.offspring o with o.mother.father = :cousin" )
				.setEntity( "cousin", s.load( Human.class, Long.valueOf( "123" ) ) )
				.list();
		assertTrue( "ad-hoc did take effect", list.isEmpty() );

		txn.commit();
		s.close();

		data.cleanup();
	}
