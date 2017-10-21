	@Test(timeout = 1000 * 30)
	@TestForIssue(jiraKey = "")
	public void testStatementIsClosed() {

		TransactionUtil.doInJPA( this::entityManagerFactory, em1 -> {

			Map<String, Object> properties = new HashMap<>();
			properties.put( org.hibernate.cfg.AvailableSettings.JPA_LOCK_TIMEOUT, 0L );
			Lock lock2 = em1.find( Lock.class, lockId, LockModeType.PESSIMISTIC_WRITE, properties );
			assertEquals(
				"lock mode should be PESSIMISTIC_WRITE ",
				LockModeType.PESSIMISTIC_WRITE,
				em1.getLockMode( lock2 )
			);

			TransactionUtil.doInJPA( this::entityManagerFactory, em2 -> {
				TransactionUtil.setJdbcTimeout( em2.unwrap( Session.class ) );
				try {
					em2.find( Lock.class, lockId, LockModeType.PESSIMISTIC_WRITE, properties );
					fail( "Exception should be thrown" );
				}
				catch (Exception lte) {
					if( !ExceptionUtil.isSqlLockTimeout( lte )) {
						fail("Should have thrown a Lock timeout exception");
					}
				}
				finally {
					try {
						for ( PreparedStatement statement : CONNECTION_PROVIDER.getPreparedStatements() ) {
							assertThat(
								"A SQL Statement was not closed : " + statement.toString(),
								statement.isClosed(),
								is( true )
							);
						}
					}
					catch (SQLException e) {
						fail( e.getMessage() );
					}
				}
			} );

		} );
	}
