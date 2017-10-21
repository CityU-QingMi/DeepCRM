	@Test
	public void testManyToOne() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Postman pm = new Postman( "Bob", "A01" );
		House house = new House();
		house.setPostman( pm );
		house.setAddress( "Rue des pres" );
		s.persist( pm );
		s.persist( house );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		house = (House) s.get( House.class, house.getId() );
		assertNotNull( house.getPostman() );
		assertEquals( "Bob", house.getPostman().getName() );
		pm = house.getPostman();
		s.delete( house );
		s.delete( pm );
		tx.commit();
		s.close();
	}
