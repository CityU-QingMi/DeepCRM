	@Test
	public void testDefaultMetadata() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Color c = new Color();
		c.setName( "Blue" );
		s.persist( c );
		Car car = new Car();
		car.setBodyColor( c );
		s.persist( car );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		car = (Car) s.get( Car.class, car.getId() );
		assertNotNull( car );
		assertNotNull( car.getBodyColor() );
		assertEquals( c.getId(), car.getBodyColor().getId() );
		tx.rollback();
		s.close();
	}
