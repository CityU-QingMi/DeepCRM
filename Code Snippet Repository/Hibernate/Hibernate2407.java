	public void onDirtyCheck(DirtyCheckEvent event) throws HibernateException {
		int oldSize = event.getSession().getActionQueue().numberOfCollectionRemovals();

		try {
			flushEverythingToExecutions(event);
			boolean wasNeeded = event.getSession().getActionQueue().hasAnyQueuedActions();
			if ( wasNeeded ) {
				LOG.debug( "Session dirty" );
			}
			else {
				LOG.debug( "Session not dirty" );
			}
			event.setDirty( wasNeeded );
		}
		finally {
			event.getSession().getActionQueue().clearFromFlushNeededCheck( oldSize );
		}
	}
