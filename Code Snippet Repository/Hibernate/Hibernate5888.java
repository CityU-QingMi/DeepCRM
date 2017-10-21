	@Test
	@RequiresDialectFeature( value = DialectChecks.SupportSkipLocked.class )
	public void testUpdateWithPessimisticReadLockSkipLocked() {
		Lock lock = new Lock();
		lock.setName( "name" );

		doInJPA( this::entityManagerFactory, entityManager -> {
			entityManager.persist( lock );
		} );

		doInJPA( this::entityManagerFactory, _entityManagaer -> {
			Map<String, Object> properties = new HashMap<>();
			properties.put( org.hibernate.cfg.AvailableSettings.JPA_LOCK_TIMEOUT, LockOptions.SKIP_LOCKED );
			_entityManagaer.find( Lock.class, lock.getId(), LockModeType.PESSIMISTIC_READ, properties );

				doInJPA( this::entityManagerFactory, entityManager -> {
					TransactionUtil.setJdbcTimeout( entityManager.unwrap( Session.class ) );
					try {
						entityManager.createNativeQuery( updateStatement() )
							.setParameter( "name", "changed" )
							.setParameter( "id", lock.getId() )
							.executeUpdate();
						fail("Should throw Exception");
					}
					catch (Exception e) {
						if ( !ExceptionUtil.isSqlLockTimeout( e) ) {
							fail( "Unknown exception thrown: " + e.getMessage() );
						}
					}
				} );

		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Lock _lock = entityManager.merge( lock );
			entityManager.remove( _lock );
		} );
	}
