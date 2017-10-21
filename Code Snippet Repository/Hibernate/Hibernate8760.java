	public void testQuerySetLockModeNONEDoNotLogAWarnMessageWhenTheDialectUseFollowOnLockingIsTrue() {
		try (Session s = openSession();) {
			final Query query = s.createQuery( "from Item i join i.bids b where name = :name" );
			query.setParameter( "name", "ZZZZ" );
			query.setLockMode( "i", LockMode.NONE );
			query.setLockMode( "b", LockMode.NONE );
			query.list();
			assertFalse( triggerable.triggerMessage(), triggerable.wasTriggered() );
		}
	}
