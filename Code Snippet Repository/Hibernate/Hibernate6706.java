	@Test
	public void testPolymorphism() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Plane p = new Plane();
		p.setNbrOfSeats( 10 );
		A320 a = new A320();
		a.setJavaEmbeddedVersion( "5.0" );
		a.setNbrOfSeats( 300 );
		s.persist( a );
		s.persist( p );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		Query q = s.createQuery( "from " + A320.class.getName() );
		List a320s = q.list();
		assertNotNull( a320s );
		assertEquals( 1, a320s.size() );
		assertTrue( a320s.get( 0 ) instanceof A320 );
		assertEquals( "5.0", ( (A320) a320s.get( 0 ) ).getJavaEmbeddedVersion() );
		q = s.createQuery( "from " + Plane.class.getName() );
		List planes = q.list();
		assertNotNull( planes );
		assertEquals( 2, planes.size() );
		tx.commit();
		s.close();
	}
