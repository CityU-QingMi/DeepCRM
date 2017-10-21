	@Test
	public void testLockWriteOnUnversioned() throws Exception {
		final UnversionedLock lock = new UnversionedLock();
		lock.setName( "second" );

		doInJPA( this::entityManagerFactory, em -> {
			em.persist( lock );
		} );

		doInJPA( this::entityManagerFactory, em -> {
			UnversionedLock _lock = em.getReference( UnversionedLock.class, lock.getId() );
			try {
				// getting a READ (optimistic) lock on unversioned entity is not expected to work.
				// To get the same functionality as prior release, change the  LockModeType.READ lock to:
				// em.lock(lock,LockModeType.PESSIMISTIC_READ);
				em.lock( _lock, LockModeType.READ );
				fail( "expected OptimisticLockException exception" );
			}
			catch ( OptimisticLockException expected ) {
			}
		} );

		doInJPA( this::entityManagerFactory, em -> {
			// the previous code block can be rewritten as follows (to get the previous behavior)
			UnversionedLock _lock = em.getReference( UnversionedLock.class, lock.getId() );
			em.lock( _lock, LockModeType.PESSIMISTIC_READ );
		} );

		doInJPA( this::entityManagerFactory, em -> {
			UnversionedLock _lock = em.getReference( UnversionedLock.class, lock.getId() );
			em.remove( _lock );
		} );
	}
