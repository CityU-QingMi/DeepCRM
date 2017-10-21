	@Test
	public void testParameterizedAuto() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Home h = new Home();
		s.persist(h);
		tx.commit();
		s.close();
		assertNotNull(h.getId());

		s = openSession();
		tx = s.beginTransaction();
		Home reloadedHome = (Home) s.get(Home.class, h.getId());
		assertEquals( h.getId(), reloadedHome.getId() );
		s.delete(reloadedHome);
		tx.commit();
		s.close();
	}
