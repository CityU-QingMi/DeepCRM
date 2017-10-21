	@Test
	public void testNativeSql() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		NativeQuery query = em.createNativeQuery( "select * from lockable l" ).unwrap( NativeQuery.class );

		// the spec disallows calling setLockMode in a native SQL query
		try {
			query.setLockMode( LockModeType.READ );
			fail( "Should have failed" );
		}
		catch (IllegalStateException expected) {
		}

		// however, we should be able to set it using hints
		query.setHint( QueryHints.HINT_NATIVE_LOCKMODE, LockModeType.READ );
		// NOTE : LockModeType.READ should map to LockMode.OPTIMISTIC
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
