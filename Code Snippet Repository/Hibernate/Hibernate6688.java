	@Test
	public void testEntityKeyElementTarget() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Atmosphere atm = new Atmosphere();
		Gas o2 = new Gas();
		o2.name = "oxygen";
		atm.composition.put( o2, 94.3 );
		s.persist( o2 );
		s.persist( atm );

		s.flush();
		s.clear();

		atm = (Atmosphere) s.get( Atmosphere.class, atm.id );
		assertTrue( ! Hibernate.isInitialized( atm.composition ) );
		assertEquals( 1, atm.composition.size() );
		assertEquals( o2.name, atm.composition.keySet().iterator().next().name );
		tx.rollback();
		s.close();
	}
