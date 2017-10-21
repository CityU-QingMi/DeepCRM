	private void firePersist(Map copiedAlready, PersistEvent event) {
		checkTransactionSynchStatus();

		try {
			for ( PersistEventListener listener : listeners( EventType.PERSIST ) ) {
				listener.onPersist( event, copiedAlready );
			}
		}
		catch ( MappingException e ) {
			throw exceptionConverter.convert( new IllegalArgumentException( e.getMessage() ) ) ;
		}
		catch ( RuntimeException e ) {
			throw exceptionConverter.convert( e );
		}
		finally {
			delayedAfterCompletion();
		}
	}
