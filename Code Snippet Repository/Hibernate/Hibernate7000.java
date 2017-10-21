	@Test
	public void testGenerated() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Antenna antenna = new Antenna();
		antenna.id = new Integer(1);
		s.persist( antenna );
		assertNull( antenna.latitude );
		assertNull( antenna.longitude );
		tx.commit();
		s.close();
	}
