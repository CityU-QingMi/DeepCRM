	@Override
	public void afterTransactionCompletion(boolean successful, boolean delayed) {
		log.tracef( "SessionImpl#afterTransactionCompletion(successful=%s, delayed=%s)", successful, delayed );

		if ( !isClosed() || waitingForAutoClose ) {
			if ( autoClear ||!successful ) {
				internalClear();
			}
		}

		persistenceContext.afterTransactionCompletion();
		actionQueue.afterTransactionCompletion( successful );

		getEventListenerManager().transactionCompletion( successful );

		if ( getFactory().getStatistics().isStatisticsEnabled() ) {
			getFactory().getStatistics().endTransaction( successful );
		}

		try {
			getInterceptor().afterTransactionCompletion( getCurrentTransaction() );
		}
		catch (Throwable t) {
			log.exceptionInAfterTransactionCompletionInterceptor( t );
		}

		if ( !delayed ) {
			if ( shouldAutoClose() && (!isClosed() || waitingForAutoClose) ) {
				managedClose();
			}
		}
	}
