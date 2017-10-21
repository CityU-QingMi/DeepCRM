	@Test
	public void testLockOptimistic() throws Exception {
		final Lock lock = new Lock();
		lock.setName( "name" );

		doInJPA( this::entityManagerFactory, em -> {
			em.persist( lock );
		} );

		doInJPA( this::entityManagerFactory, em -> {
			Lock _lock = em.getReference( Lock.class, lock.getId() );
			em.lock( _lock, LockModeType.OPTIMISTIC );
			_lock.setName( "surname" );
		} );

		doInJPA( this::entityManagerFactory, em -> {
			Lock _lock = em.find( Lock.class, lock.getId() );
			assertEquals( "surname", _lock.getName() );
		} );

		doInJPA( this::entityManagerFactory, em -> {
			Lock _lock = em.find( Lock.class, lock.getId() );
			em.remove( _lock );
		} );
	}
