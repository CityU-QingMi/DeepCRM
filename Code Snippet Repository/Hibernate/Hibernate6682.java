	@Test
	public void testRealMap() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Atmosphere atm = new Atmosphere();
		Atmosphere atm2 = new Atmosphere();
		GasKey key = new GasKey();
		key.setName( "O2" );
		Gas o2 = new Gas();
		o2.name = "oxygen";
		atm.gases.put( "100%", o2 );
		atm.gasesPerKey.put( key, o2 );
		atm2.gases.put( "100%", o2 );
		atm2.gasesPerKey.put( key, o2 );
		s.persist( key );
		s.persist( atm );
		s.persist( atm2 );

		s.flush();
		s.clear();

		atm = (Atmosphere) s.get( Atmosphere.class, atm.id );
		key = (GasKey) s.get( GasKey.class, key.getName() );
		assertEquals( 1, atm.gases.size() );
		assertEquals( o2.name, atm.gases.get( "100%" ).name );
		assertEquals( o2.name, atm.gasesPerKey.get( key ).name );
		tx.rollback();
		s.close();
	}
