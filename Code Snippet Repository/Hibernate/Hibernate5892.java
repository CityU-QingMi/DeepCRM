	@Test
	public void testLockWrite() throws Exception {
		final Lock lock = new Lock();
		lock.setName( "second" );

		doInJPA( this::entityManagerFactory, em -> {
			em.persist( lock );
		} );

		Integer version = doInJPA( this::entityManagerFactory, em -> {
			Lock _lock = em.getReference( Lock.class, lock.getId() );
			Integer _version = _lock.getVersion();
			em.lock( _lock, LockModeType.WRITE );
			return _version;
		} );


		try {
			doInJPA( this::entityManagerFactory, em -> {
				Lock _lock = em.getReference( Lock.class, lock.getId() );
				assertEquals( "should increase the version number EJB-106", 1, _lock.getVersion() - version );
			} );
		}
		finally {
			doInJPA( this::entityManagerFactory, em -> {
				Lock _lock = em.getReference( Lock.class, lock.getId() );
				em.remove( _lock );
			} );
		}
	}
