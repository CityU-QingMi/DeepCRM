	protected boolean autoFlushIfRequired(Set querySpaces) throws HibernateException {
		checkOpen();
		if ( !isTransactionInProgress() ) {
			// do not auto-flush while outside a transaction
			return false;
		}
		AutoFlushEvent event = new AutoFlushEvent( querySpaces, this );
		listeners( EventType.AUTO_FLUSH );
		for ( AutoFlushEventListener listener : listeners( EventType.AUTO_FLUSH ) ) {
			listener.onAutoFlush( event );
		}
		return event.isFlushRequired();
	}
