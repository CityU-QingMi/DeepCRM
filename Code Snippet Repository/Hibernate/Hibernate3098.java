	private void fireRefresh(Map refreshedAlready, RefreshEvent event) {
		try {
			checkTransactionSynchStatus();
			for ( RefreshEventListener listener : listeners( EventType.REFRESH ) ) {
				listener.onRefresh( event, refreshedAlready );
			}
			delayedAfterCompletion();
		}
		catch (RuntimeException e) {
			throw exceptionConverter.convert( e );
		}
		finally {
			delayedAfterCompletion();
		}
	}
