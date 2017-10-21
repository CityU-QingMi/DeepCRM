	private void nowAttemptToUpdateRow() {
		// here we just need to open a new connection (database session and transaction) and make sure that
		// we are not allowed to acquire exclusive locks to that row and/or write to that row.  That may take
		// one of two forms:
		//		1) either the get-with-lock or the update fails immediately with a sql error
		//		2) either the get-with-lock or the update blocks indefinitely (in real world, it would block
		//			until the txn in the calling method completed.
		// To be able to cater to the second type, we run this block in a separate thread to be able to "time it out"

		try {
			executeSync( () -> {
				doInHibernate( this::sessionFactory, _session -> {
					TransactionUtil.setJdbcTimeout( _session );
					try {
						// load with write lock to deal with databases that block (wait indefinitely) direct attempts
						// to write a locked row
						A it = _session.get(
								A.class,
								id,
								new LockOptions( LockMode.PESSIMISTIC_WRITE ).setTimeOut( LockOptions.NO_WAIT )
						);
						_session.createNativeQuery( updateStatement() )
								.setParameter( "value", "changed" )
								.setParameter( "id", it.getId() )
								.executeUpdate();
						fail( "Pessimistic lock not obtained/held" );
					}
					catch ( Exception e ) {
						if ( !ExceptionUtil.isSqlLockTimeout( e) ) {
							fail( "Unexpected error type testing pessimistic locking : " + e.getClass().getName() );
						}
					}
				} );
			} );
		}
		catch (Exception e) {
			//MariaDB throws a time out nd closes the underlying connection
			if( !ExceptionUtil.isConnectionClose(e)) {
				fail("Unknown exception thrown: " + e.getMessage());
			}
		}
	}
