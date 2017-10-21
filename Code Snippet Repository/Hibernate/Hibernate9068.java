	@Test
	@FailureExpected( jiraKey = "", message = "" )
	public void testRefreshLockUninitializedProxy() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		DataPoint dp = newPersistentDataPoint( s );

		dp = ( DataPoint ) s.load( DataPoint.class, new Long( dp.getId() ) );
		assertFalse( Hibernate.isInitialized( dp ) );

		s.refresh( dp, LockOptions.UPGRADE );
		assertSame( LockOptions.UPGRADE.getLockMode(), s.getCurrentLockMode( dp ) );

		s.delete( dp );
		t.commit();
		s.close();
	}
