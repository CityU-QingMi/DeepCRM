	private void doFlush() {
		checkTransactionNeeded();
		checkTransactionSynchStatus();

		try {
			if ( persistenceContext.getCascadeLevel() > 0 ) {
				throw new HibernateException( "Flush during cascade is dangerous" );
			}

			FlushEvent flushEvent = new FlushEvent( this );
			for ( FlushEventListener listener : listeners( EventType.FLUSH ) ) {
				listener.onFlush( flushEvent );
			}

			delayedAfterCompletion();
		}
		catch ( RuntimeException e ) {
			throw exceptionConverter.convert( e );
		}
	}
