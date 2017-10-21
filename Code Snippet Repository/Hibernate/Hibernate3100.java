	@Override
	public boolean isDirty() throws HibernateException {
		checkOpen();
		checkTransactionSynchStatus();
		log.debug( "Checking session dirtiness" );
		if ( actionQueue.areInsertionsOrDeletionsQueued() ) {
			log.debug( "Session dirty (scheduled updates and insertions)" );
			return true;
		}
		DirtyCheckEvent event = new DirtyCheckEvent( this );
		for ( DirtyCheckEventListener listener : listeners( EventType.DIRTY_CHECK ) ) {
			listener.onDirtyCheck( event );
		}
		delayedAfterCompletion();
		return event.isDirty();
	}
