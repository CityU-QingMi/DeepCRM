	@Test
	public void testManyToManyPropertyRef() {
		// prepare some test data relating to the Group->Person many-to-many association
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Person p = new Person();
		p.setName( "Steve" );
		p.setUserId( "steve" );
		s.persist( p );
		Group g = new Group();
		g.setName( "Admins" );
		g.getUsers().add( p );
		s.persist( g );
		// force a flush and detachment here to test reattachment handling of the property-ref (HHH-1531)
		t.commit();
		s.close();

		Person p2 = new Person();
		p2.setName( "Max" );
		p2.setUserId( "max" );
		g.getUsers().add( p2 );

		s = openSession();
		t = s.beginTransaction();
		s.update( g );
		t.commit();
		s.close();

		// test retrieval of the group
		s = openSession();
		t = s.beginTransaction();
		g = ( Group ) s.createQuery( "from Group g left join fetch g.users" ).uniqueResult();
		assertTrue( Hibernate.isInitialized( g.getUsers() ) );
		assertEquals( 2, g.getUsers().size() );
		s.delete( g );
		s.createQuery( "delete Person" ).executeUpdate();
		t.commit();
		s.close();
	}
