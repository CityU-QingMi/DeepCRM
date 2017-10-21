	@Test
	public void testAnnotationsDefaultNullOrdering() {
		Session session = openSession();

		// Populating database with test data.
		session.getTransaction().begin();
		Troop troop = new Troop();
		troop.setName( "Alpha 1" );
		Soldier ranger = new Soldier();
		ranger.setName( "Ranger 1" );
		troop.addSoldier( ranger );
		Soldier sniper = new Soldier();
		sniper.setName( null );
		troop.addSoldier( sniper );
		session.persist( troop );
		session.getTransaction().commit();

		session.clear();

		session.getTransaction().begin();
		troop = (Troop) session.get( Troop.class, troop.getId() );
		Iterator<Soldier> iterator = troop.getSoldiers().iterator(); // Should order by NULLS LAST.
		Assert.assertEquals( ranger.getName(), iterator.next().getName() );
		Assert.assertNull( iterator.next().getName() );
		session.getTransaction().commit();

		session.clear();

		// Cleanup data.
		session.getTransaction().begin();
		session.delete( troop );
		session.getTransaction().commit();

		session.close();
	}
