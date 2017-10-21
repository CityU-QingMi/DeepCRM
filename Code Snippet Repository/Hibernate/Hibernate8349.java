	@Test
	public void testTransactionEvents() throws Exception {
        LoggingInterceptor interceptor = new LoggingInterceptor();

        Session s = openSession(interceptor);
		Transaction tx = s.beginTransaction();
		// Do nothing, open and closing the transaction is enough
		tx.commit();
		s.close();

        assertTrue("afterTransactionBeginCalled not called", interceptor.isAfterTransactionBeginCalled());
        assertTrue("afterTransactionCompletionCalled not called", interceptor.isAfterTransactionCompletionCalled());
        assertTrue("beforeTransactionCompletionCalled not called", interceptor.isBeforeTransactionCompletionCalled());
    }
