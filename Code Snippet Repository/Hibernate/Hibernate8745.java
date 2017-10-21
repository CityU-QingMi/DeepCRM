	@Test
	public void testLegacyCriteria() {
		// open a session, begin a transaction and lock row
		doInHibernate( this::sessionFactory, session -> {

			A it = (A) session.createCriteria( A.class )
					.setLockMode( LockMode.PESSIMISTIC_WRITE )
					.uniqueResult();
			// make sure we got it
			assertNotNull( it );

			// that initial transaction is still active and so the lock should still be held.
			// Lets open another session/transaction and verify that we cannot update the row
			nowAttemptToUpdateRow();
		} );
	}
