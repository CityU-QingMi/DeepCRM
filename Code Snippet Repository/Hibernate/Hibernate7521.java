	public void testSetLockModeNONEDoNotLogAWarnMessageWhenTheDialectUseFollowOnLockingIsTrue() {
		buildSessionFactory();
		Triggerable triggerable = logInspection.watchForLogMessages( "HHH000444" );

		final Session s = openSession();
		final Transaction tx = s.beginTransaction();

		Item item = new Item();
		item.name = "ZZZZ";
		s.persist( item );

		s.flush();

		Criteria criteria = s.createCriteria( Item.class )
				.setLockMode( LockMode.NONE );

		criteria.list();

		tx.rollback();
		s.close();

		releaseSessionFactory();

		assertFalse( triggerable.wasTriggered() );
	}
