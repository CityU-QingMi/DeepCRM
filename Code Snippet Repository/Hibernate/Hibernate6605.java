	@Test
	public void testNoGenerator() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Hotel hotel = new Hotel();
		hotel.setId( 12l );
		hotel.setName("California");
		s.saveOrUpdate(hotel);
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		hotel = (Hotel) s.get(Hotel.class, 12l);
		assertNotNull(hotel);
		assertEquals("California", hotel.getName());
		assertNull(s.get(Hotel.class, 13l));
		tx.commit();

		s = openSession();
		tx = s.beginTransaction();
		//hotel is now detached
		hotel.setName("Hotel du nord");
		s.saveOrUpdate(hotel);
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		hotel = (Hotel) s.get(Hotel.class, 12l);
		assertNotNull(hotel);
		assertEquals("Hotel du nord", hotel.getName());
		s.delete(hotel);
		tx.commit();
		s.close();
	}
