	@Test
	@RequiresDialectFeature(value = DialectChecks.SupportsLockTimeouts.class)
	public void testUpdateWithPessimisticReadLockWithoutNoWait() {
		Lock lock = new Lock();
		lock.setName( "name" );

		doInJPA( this::entityManagerFactory, entityManager -> {
			entityManager.persist( lock );
		} );

		doInJPA( this::entityManagerFactory, _entityManager -> {
			_entityManager.find( Lock.class, lock.getId(), LockModeType.PESSIMISTIC_READ );

			AtomicBoolean failureExpected = new AtomicBoolean();

			try {
				doInJPA( this::entityManagerFactory, entityManager -> {
					try {
						TransactionUtil.setJdbcTimeout( entityManager.unwrap( Session.class ) );
						entityManager.createNativeQuery( updateStatement() )
								.setParameter( "name", "changed" )
								.setParameter( "id", lock.getId() )
								.executeUpdate();
					}
					catch (Exception e) {
						if ( ExceptionUtil.isSqlLockTimeout( e ) ) {
							failureExpected.set( true );
						}
					}
				} );
			}
			catch (Exception e) {
				if ( !failureExpected.get() ) {
					fail( "Should throw LockTimeoutException or PessimisticLockException" );
				}
			}
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Lock _lock = entityManager.merge( lock );
			entityManager.remove( _lock );
		} );
	}
