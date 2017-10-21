	@Test
	@SuppressWarnings( {""})
	public void testLoading() {
		// open a session, begin a transaction and lock row
		doInHibernate( this::sessionFactory, session -> {
			A it = session.byId( A.class ).with( LockOptions.UPGRADE ).load( id );
			// make sure we got it
			assertNotNull( it );

			// that initial transaction is still active and so the lock should still be held.
			// Lets open another session/transaction and verify that we cannot update the row
			nowAttemptToUpdateRow();
		} );
	}
