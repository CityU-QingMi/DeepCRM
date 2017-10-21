	@Test
	public void testImplicitNativeQuery() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		SpaceShip ship = new SpaceShip();
		ship.setModel( "X-Wing" );
		ship.setName( "YuBlue" );
		ship.setSpeed( 2000 );
		ship.setDimensions( new Dimensions() );
		s.persist( ship );
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		Query q = s.getNamedQuery( "implicitSample" );
		List result = q.list();
		assertEquals( 1, result.size() );
		assertEquals( ship.getModel(), ( (SpaceShip) result.get( 0 ) ).getModel() );
		s.delete( result.get( 0 ) );
		tx.commit();
		s.close();
	}
