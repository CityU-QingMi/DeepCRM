	@Override
	protected void release(Session session) {
		if ( session.getTransaction().getStatus() != TransactionStatus.ACTIVE ) {
			TestableThreadLocalContext.unbind( sessionFactory() );
			return;
		}
		long initialCount = sessionFactory().getStatistics().getSessionCloseCount();
		session.getTransaction().commit();
		long subsequentCount = sessionFactory().getStatistics().getSessionCloseCount();
		assertEquals( "Session still open after commit", initialCount + 1, subsequentCount );
		// also make sure it was cleaned up from the internal ThreadLocal...
		assertFalse( "session still bound to internal ThreadLocal", TestableThreadLocalContext.hasBind() );
	}
