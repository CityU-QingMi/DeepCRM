	@Test
	public void testLockOptimisticForceIncrement() throws Exception {
		final Lock lock = new Lock();
		lock.setName( "force" );

		doInJPA( this::entityManagerFactory, em -> {
			em.persist( lock );
		} );

		Integer version = doInJPA( this::entityManagerFactory, em -> {
			Lock _lock = em.getReference( Lock.class, lock.getId() );
			Integer _version = _lock.getVersion();
			em.lock( _lock, LockModeType.OPTIMISTIC_FORCE_INCREMENT );

			return _version;
		} );

		doInJPA( this::entityManagerFactory, em -> {
			Lock _lock = em.getReference( Lock.class, lock.getId() );
			assertEquals( "should increase the version number ", 1, _lock.getVersion() - version );
		} );

		doInJPA( this::entityManagerFactory, em -> {
			Lock _lock = em.find( Lock.class, lock.getId() );
			em.remove( _lock );
		} );
	}
