	@Test
	public void testRollingBack() throws Throwable {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		int testLength = 3;
		Long lastId = null;
		for (int i = 0; i < testLength ; i++) {
			Car car = new Car();
			car.setColor( "color " + i );
			s.save( car );
			lastId = car.getId();
		}
		tx.rollback();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		Car car = new Car();
		car.setColor( "blue" );
		s.save( car );
		s.flush();
		tx.commit();
		s.close();

		assertEquals( "id generation was rolled back", lastId.longValue() + 1, car.getId().longValue() );

		s = openSession();
		tx = s.beginTransaction();
		s.createQuery( "delete Car" ).executeUpdate();
		tx.commit();
		s.close();
	}
