	@Test
	public void testSimpleNaturalIdLoadAccessCacheWithUpdate() {
		Session s = openSession();
		s.beginTransaction();
		Group g = new Group( 1, "admin" );
		s.persist( g );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		g = (Group) s.bySimpleNaturalId( Group.class ).load( "admin" );
		assertNotNull( g );
		Group g2 = (Group) s.bySimpleNaturalId( Group.class ).getReference( "admin" );
		assertTrue( g == g2 );
		g.setName( "admins" );
		s.flush();
		g2 = (Group) s.bySimpleNaturalId( Group.class ).getReference( "admins" );
		assertTrue( g == g2 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.createQuery( "delete Group" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
