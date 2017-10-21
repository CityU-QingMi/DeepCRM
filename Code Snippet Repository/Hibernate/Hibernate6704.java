	@Test
	public void testEnumKeyType() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Atmosphere atm = new Atmosphere();
		atm.colorPerLevel.put( Atmosphere.Level.HIGH, "red" );
		s.persist( atm );

		s.flush();
		s.clear();

		atm = (Atmosphere) s.get( Atmosphere.class, atm.id );
		assertEquals( 1, atm.colorPerLevel.size() );
		assertEquals( "red", atm.colorPerLevel.get( Atmosphere.Level.HIGH) );
		tx.rollback();
		s.close();
	}
