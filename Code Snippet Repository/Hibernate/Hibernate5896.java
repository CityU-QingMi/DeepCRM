	@Test
	@TestForIssue( jiraKey = "")
	public void testTimeoutHint(){
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		boolean b= em.getProperties().containsKey( AvailableSettings.LOCK_TIMEOUT );
		assertTrue( b );
		int timeout = Integer.valueOf( em.getProperties().get( AvailableSettings.LOCK_TIMEOUT ).toString() );
		assertEquals( 2000, timeout);
		org.hibernate.query.Query q = (org.hibernate.query.Query) em.createQuery( "select u from UnversionedLock u" );
		timeout = q.getLockOptions().getTimeOut();
		assertEquals( 2000, timeout );

		Query query = em.createQuery( "select u from UnversionedLock u" );
		query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
		query.setHint( AvailableSettings.LOCK_TIMEOUT, 3000 );
		q = (org.hibernate.query.Query) query;
		timeout = q.getLockOptions().getTimeOut();
		assertEquals( 3000, timeout );
		em.getTransaction().rollback();
		em.close();
	}
