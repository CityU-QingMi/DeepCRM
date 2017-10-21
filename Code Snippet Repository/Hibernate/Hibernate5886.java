	@Test
	public void testLockOptimisticForceIncrementDifferentEm() throws Exception {
		final Lock lock = new Lock();
		lock.setName( "force" );

		doInJPA( this::entityManagerFactory, em -> {
			em.persist( lock );
		} );

		doInJPA( this::entityManagerFactory, em -> {
			Lock _lock = em.find( Lock.class, lock.getId(), LockModeType.OPTIMISTIC );
			assertEquals( "lock mode should be OPTIMISTIC ", LockModeType.OPTIMISTIC, em.getLockMode( _lock ) );
			em.lock( _lock, LockModeType.OPTIMISTIC_FORCE_INCREMENT );
			assertEquals(
					"lock mode should be OPTIMISTIC_FORCE_INCREMENT ",
					LockModeType.OPTIMISTIC_FORCE_INCREMENT,
					em.getLockMode( _lock )
			);
		} );

		doInJPA( this::entityManagerFactory, em -> {
			Lock _lock = em.find( Lock.class, lock.getId() );
			em.remove( _lock );
		} );
	}
