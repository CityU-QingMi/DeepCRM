	@Test
	public void testFormula() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Rock guns = new Rock();
		guns.setAvgBeat( 90 );
		guns.setType( 2 );
		Noise white = new Noise();
		white.setAvgBeat( 0 );
		white.setType( null );

		s.persist( guns );
		s.persist( white );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		List result = s.createCriteria( Noise.class ).list();
		assertNotNull( result );
		assertEquals( 1, result.size() );
		white = (Noise) result.get( 0 );
		assertNull( white.getType() );
		s.delete( white );
		result = s.createCriteria( Rock.class ).list();
		assertEquals( 1, result.size() );
		s.delete( result.get( 0 ) );
		result = s.createCriteria( Funk.class ).list();
		assertEquals( 0, result.size() );

		tx.commit();
		s.close();
	}
