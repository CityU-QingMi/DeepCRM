	@Test
	public void testFindWithTimeoutHint() {
		final Lock lock = new Lock();
		lock.setName( "name" );

		doInJPA( this::entityManagerFactory, em -> {
			em.persist( lock );
			return lock.getId();
		} );

		doInJPA( this::entityManagerFactory, em -> {
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put( AvailableSettings.LOCK_TIMEOUT, 0L );
			em.find( Lock.class, 1, LockModeType.PESSIMISTIC_WRITE, properties );
		} );

		doInJPA( this::entityManagerFactory, em -> {
			Lock _lock = em.find( Lock.class, lock.getId() );
			em.remove( _lock );
		} );
	}
