	private static void cleanupAnyOrphanedSession(SessionFactory factory) {
		final Session orphan = doUnbind( factory, false );
		if ( orphan != null ) {
			LOG.alreadySessionBound();
			try {
				if ( orphan.getTransaction() != null && orphan.getTransaction().getStatus() == TransactionStatus.ACTIVE ) {
					try {
						orphan.getTransaction().rollback();
					}
					catch( Throwable t ) {
						LOG.debug( "Unable to rollback transaction for orphaned session", t );
					}
				}
				orphan.close();
			}
			catch( Throwable t ) {
				LOG.debug( "Unable to close orphaned session", t );
			}
		}
	}
