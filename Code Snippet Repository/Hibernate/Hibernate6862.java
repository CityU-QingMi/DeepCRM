	@Test
	public void testCascadeDelete() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Troop disney = new Troop();
		disney.setName( "Disney" );
		Soldier mickey = new Soldier();
		mickey.setName( "Mickey" );
		disney.addSoldier( mickey );
		s.persist( disney );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		Troop troop = ( Troop ) s.get( Troop.class, disney.getId() );
		s.delete( troop );
		tx.commit();
		s.close();
		s = openSession();
		tx = s.beginTransaction();
		Soldier soldier = ( Soldier ) s.get( Soldier.class, mickey.getId() );
		assertNull( "delete-orphan should work", soldier );
		tx.commit();
		s.close();
	}
