	@Test
	public void testEager() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Color c = new Color();
		c.setName( "Yellow" );
		s.persist( c );
		Car car = new Car();
		car.setBodyColor( c );
		s.persist( car );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		car = (Car) s.get( Car.class, car.getId() );
		tx.commit();
		s.close();
		assertNotNull( car );
		assertNotNull( car.getBodyColor() );
		assertEquals( "Yellow", car.getBodyColor().getName() );
	}
