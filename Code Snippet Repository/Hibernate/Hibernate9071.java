	@Test
	public void testLockUninitializedProxy() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		DataPoint dp = newPersistentDataPoint( s );

		dp = ( DataPoint) s.load( DataPoint.class, new Long( dp.getId() ) );
		assertFalse( Hibernate.isInitialized( dp ) );
		s.buildLockRequest( LockOptions.UPGRADE ).lock( dp );
		assertSame( LockOptions.UPGRADE.getLockMode(), s.getCurrentLockMode( dp ) );

		s.delete( dp );
		t.commit();
		s.close();
	}
