	@Test
	public void testNativeQueryAndCompositePKAndComponents() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		SpaceShip ship = new SpaceShip();
		ship.setModel( "X-Wing" );
		ship.setName( "YuBlue" );
		ship.setSpeed( 2000 );
		ship.setDimensions( new Dimensions() );
		ship.getDimensions().setLength( 10 );
		ship.getDimensions().setWidth( 5 );
		Captain captain = new Captain();
		captain.setFirstname( "Luke" );
		captain.setLastname( "Skywalker" );
		ship.setCaptain( captain );
		s.persist( captain );
		s.persist( ship );
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		Query q = s.getNamedQuery( "compositekey" );
		List result = q.list();
		assertEquals( 1, result.size() );
		Object[] row = (Object[]) result.get( 0 );
		SpaceShip spaceShip = (SpaceShip) row[0];
		assertEquals( ship.getModel(), spaceShip.getModel() );
		assertNotNull( spaceShip.getDimensions() );
		assertEquals( ship.getDimensions().getWidth(), spaceShip.getDimensions().getWidth() );
		assertEquals( ship.getDimensions().getLength(), spaceShip.getDimensions().getLength() );
		assertEquals( ship.getCaptain().getFirstname(), ship.getCaptain().getFirstname() );
		assertEquals( ship.getCaptain().getLastname(), ship.getCaptain().getLastname() );
		//FIXME vary depending on databases
		assertTrue( row[1].toString().startsWith( "50" ) );
		assertTrue( row[2].toString().startsWith( "500" ) );
		s.delete( spaceShip.getCaptain() );
		s.delete( spaceShip );
		tx.commit();
		s.close();
	}
