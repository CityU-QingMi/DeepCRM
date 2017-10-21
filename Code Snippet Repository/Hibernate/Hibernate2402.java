	public void onAutoFlush(AutoFlushEvent event) throws HibernateException {
		final EventSource source = event.getSession();
		try {
			source.getEventListenerManager().partialFlushStart();

			if ( flushMightBeNeeded(source) ) {
				// Need to get the number of collection removals before flushing to executions
				// (because flushing to executions can add collection removal actions to the action queue).
				final int oldSize = source.getActionQueue().numberOfCollectionRemovals();
				flushEverythingToExecutions(event);
				if ( flushIsReallyNeeded(event, source) ) {
					LOG.trace( "Need to execute flush" );

					// note: performExecutions() clears all collectionXxxxtion
					// collections (the collection actions) in the session
					performExecutions(source);
					postFlush(source);

					postPostFlush( source );

					if ( source.getFactory().getStatistics().isStatisticsEnabled() ) {
						source.getFactory().getStatistics().flush();
					}
				}
				else {
					LOG.trace( "Don't need to execute flush" );
					source.getActionQueue().clearFromFlushNeededCheck( oldSize );
				}

				event.setFlushRequired( flushIsReallyNeeded( event, source ) );
			}
		}
		finally {
			source.getEventListenerManager().partialFlushEnd(
					event.getNumberOfEntitiesProcessed(),
					event.getNumberOfEntitiesProcessed()
			);
		}
	}
