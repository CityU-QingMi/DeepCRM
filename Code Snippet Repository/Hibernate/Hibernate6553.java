	@Test
	public void testTransientField() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		WashingMachine wm = new WashingMachine();
		wm.setActive( true );
		s.persist( wm );
		tx.commit();
		s.clear();
		tx = s.beginTransaction();
		wm = (WashingMachine) s.get( WashingMachine.class, wm.getId() );
		assertFalse( "transient should not be persistent", wm.isActive() );
		s.delete( wm );
		tx.commit();
		s.close();
	}
