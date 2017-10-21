	@Test
	public void testSelf() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Friend f = new Friend();
		Friend sndF = new Friend();
		f.setName( "Starsky" );
		sndF.setName( "Hutch" );
		Set frnds = new HashSet();
		frnds.add( sndF );
		f.setFriends( frnds );
		//Starsky is a friend of Hutch but hutch is not
		s.persist( f );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		f = (Friend) s.load( Friend.class, f.getId() );
		assertNotNull( f );
		assertNotNull( f.getFriends() );
		assertEquals( 1, f.getFriends().size() );
		Friend fromDb2ndFrnd = f.getFriends().iterator().next();
		assertEquals( fromDb2ndFrnd.getId(), sndF.getId() );
		assertEquals( 0, fromDb2ndFrnd.getFriends().size() );
		tx.commit();
		s.close();
	}
