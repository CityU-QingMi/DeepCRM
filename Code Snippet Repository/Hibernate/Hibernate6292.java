	@Test
	public void testFieldsOverriding() throws Exception {
		Gardenshed gs = new Gardenshed();
		gs.floors = 4;
		Session s = openSession();
		s.persist( gs );
		Transaction tx = s.beginTransaction();
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		gs = ( Gardenshed ) s.get( Gardenshed.class, gs.getId() );
		assertEquals( 4, gs.floors );
		assertEquals( 6, gs.getFloors() );
		s.delete( gs );
		tx.commit();
		s.close();
	}
