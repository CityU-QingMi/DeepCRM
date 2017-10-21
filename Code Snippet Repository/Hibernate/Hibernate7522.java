	public void testSetLockModeDifferentFromNONELogAWarnMessageWhenTheDialectUseFollowOnLockingIsTrue() {
		buildSessionFactory();
		Triggerable triggerable = logInspection.watchForLogMessages( "HHH000444" );

		final Session s = openSession();
		final Transaction tx = s.beginTransaction();

		Item item = new Item();
		item.name = "ZZZZ";
		s.persist( item );

		s.flush();

		Criteria criteria = s.createCriteria( Item.class )
				.setLockMode( LockMode.OPTIMISTIC );

		criteria.list();

		tx.rollback();
		s.close();
		releaseSessionFactory();

		assertTrue( triggerable.wasTriggered() );
	}
