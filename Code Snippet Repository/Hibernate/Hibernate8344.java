	@Test
	public void testTimeout() throws Exception {
		final int TIMEOUT = 2;
		final int WAIT = TIMEOUT + 1;
		Session s = openSession();
		// Get the transaction and set the timeout BEFORE calling begin()
		Transaction t = s.getTransaction();
		t.setTimeout( TIMEOUT );
		t.begin();
		// Sleep for an amount of time that exceeds the transaction timeout
		Thread.sleep( WAIT * 1000 );
        try {
        	// Do something with the transaction and try to commit it
        	s.persist( new User( "john", "test" ) );
        	t.commit();
            fail( "Transaction should have timed out" );
        }
		catch (PersistenceException e){
			assertTyping(TransactionException.class, e.getCause());
			assertTrue(
					"Transaction failed for the wrong reason.  Expecting transaction timeout, but found [" +
							e.getCause().getMessage() + "]"					,
					e.getCause().getMessage().contains( "transaction timeout expired" )
			);
		}
	}
