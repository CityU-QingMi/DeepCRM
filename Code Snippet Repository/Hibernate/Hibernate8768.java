	@Test
	public void testRemoveAndAddEqualElement() {
		deleteMembership( user, group, membership );
		membership = createMembership( "membership" );
		addMembership( user, group, membership );

		Session s = openSession();
		s.beginTransaction();
		s.merge( user );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		user = ( User ) s.get( User.class, user.getId() );
		group = ( Group ) s.get( Group.class, group.getId() );
		membership = ( Membership ) s.get( membership.getClass(), membership.getId() );
		assertEquals( "user", user.getName() );
		assertEquals( "group", group.getName() );
		assertEquals( "membership", membership.getName() );
		assertEquals( 1, user.getMemberships().size() );
		assertEquals( 1, group.getMemberships().size() );
		assertSame( membership, user.getMemberships().iterator().next() );
		assertSame( membership, group.getMemberships().iterator().next() );
		assertSame( user, membership.getUser() );
		assertSame( group, membership.getGroup() );
		s.getTransaction().commit();
		s.close();
	}
