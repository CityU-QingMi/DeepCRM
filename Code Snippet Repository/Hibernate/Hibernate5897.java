	@Test
	public void testOverallLockMode() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		org.hibernate.query.Query query = em.createQuery( "from Lockable l" ).unwrap( org.hibernate.query.Query.class );
		assertEquals( LockMode.NONE, query.getLockOptions().getLockMode() );
		assertNull( query.getLockOptions().getAliasSpecificLockMode( "l" ) );
		assertEquals( LockMode.NONE, query.getLockOptions().getEffectiveLockMode( "l" ) );

		// NOTE : LockModeType.READ should map to LockMode.OPTIMISTIC
		query.setLockMode( LockModeType.READ );
		assertEquals( LockMode.OPTIMISTIC, query.getLockOptions().getLockMode() );
		assertNull( query.getLockOptions().getAliasSpecificLockMode( "l" ) );
		assertEquals( LockMode.OPTIMISTIC, query.getLockOptions().getEffectiveLockMode( "l" ) );

		query.setHint( AvailableSettings.ALIAS_SPECIFIC_LOCK_MODE+".l", LockModeType.PESSIMISTIC_WRITE );
		assertEquals( LockMode.OPTIMISTIC, query.getLockOptions().getLockMode() );
		assertEquals( LockMode.PESSIMISTIC_WRITE, query.getLockOptions().getAliasSpecificLockMode( "l" ) );
		assertEquals( LockMode.PESSIMISTIC_WRITE, query.getLockOptions().getEffectiveLockMode( "l" ) );

		em.getTransaction().commit();
		em.close();
	}
