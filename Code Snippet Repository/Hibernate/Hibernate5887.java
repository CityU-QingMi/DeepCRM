	public void testFindWithPessimisticWriteLockTimeoutException() {
		Lock lock = new Lock();
		lock.setName( "name" );

		doInJPA( this::entityManagerFactory, entityManager -> {
			entityManager.persist( lock );
		} );

		doInJPA( this::entityManagerFactory, _entityManager -> {

			Lock lock2 = _entityManager.find( Lock.class, lock.getId(), LockModeType.PESSIMISTIC_WRITE );
			assertEquals( "lock mode should be PESSIMISTIC_WRITE ", LockModeType.PESSIMISTIC_WRITE, _entityManager.getLockMode( lock2 ) );

			doInJPA( this::entityManagerFactory, entityManager -> {
				try {
					TransactionUtil.setJdbcTimeout( entityManager.unwrap( Session.class ) );
					Map<String, Object> properties = new HashMap<String, Object>();
					properties.put( AvailableSettings.LOCK_TIMEOUT, 0L );

					entityManager.find( Lock.class, lock.getId(), LockModeType.PESSIMISTIC_WRITE, properties );
					fail( "Exception should be thrown" );
				}
				catch (LockTimeoutException lte) {
					// Proper exception thrown for dialect supporting lock timeouts when an immediate timeout is set.
					lte.getCause();
				}
				catch (PessimisticLockException pe) {
					fail( "Find with immediate timeout should have thrown LockTimeoutException." );
				}
				catch (PersistenceException pe) {
					log.info("EntityManager.find() for PESSIMISTIC_WRITE with timeout of 0 threw a PersistenceException.\n" +
									 "This is likely a consequence of " + getDialect().getClass().getName() + " not properly mapping SQL errors into the correct HibernateException subtypes.\n" +
									 "See HHH-7251 for an example of one such situation.", pe);
					fail( "EntityManager should be throwing LockTimeoutException." );
				}
			} );
		} );
	}
