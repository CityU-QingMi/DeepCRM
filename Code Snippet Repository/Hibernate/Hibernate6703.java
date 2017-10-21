	@Test
	public void testTemporalKeyMap() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Atmosphere atm = new Atmosphere();
		atm.colorPerDate.put( new Date(1234567000), "red" );
		s.persist( atm );

		s.flush();
		s.clear();

		atm = (Atmosphere) s.get( Atmosphere.class, atm.id );
		assertEquals( 1, atm.colorPerDate.size() );
		final Date date = atm.colorPerDate.keySet().iterator().next();
		final long diff = new Date( 1234567000 ).getTime() - date.getTime();
		assertTrue( "24h diff max", diff >= 0 && diff < 24*60*60*1000 );
		tx.rollback();
		s.close();
	}
