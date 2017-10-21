	@Test
	public void testEmbeddedSuperclass() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Plane p = new Plane();
		p.setAlive( true ); //sic
		p.setAltitude( 10000 );
		p.setMetricAltitude( 3000 );
		p.setNbrOfSeats( 150 );
		p.setSerial( "0123456789" );
		s.persist( p );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		p = (Plane) s.get( Plane.class, p.getId() );
		assertNotNull( p );
		assertEquals( true, p.isAlive() );
		assertEquals( 150, p.getNbrOfSeats() );
		assertEquals( 10000, p.getAltitude() );
		assertEquals( "0123456789", p.getSerial() );
		assertFalse( 3000 == p.getMetricAltitude() );
		s.delete( p );
		tx.commit();
		s.close();
	}
