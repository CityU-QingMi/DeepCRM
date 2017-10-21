	@Override
	public void beforeTransactionCompletion() {
		log.tracef( "SessionImpl#beforeTransactionCompletion()" );
		flushBeforeTransactionCompletion();
		actionQueue.beforeTransactionCompletion();
		try {
			getInterceptor().beforeTransactionCompletion( getCurrentTransaction() );
		}
		catch (Throwable t) {
			log.exceptionInBeforeTransactionCompletionInterceptor( t );
		}
	}
