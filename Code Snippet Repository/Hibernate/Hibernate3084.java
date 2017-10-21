	private void firePersist(PersistEvent event) {
		try {
			checkTransactionSynchStatus();
			checkNoUnresolvedActionsBeforeOperation();

			for ( PersistEventListener listener : listeners( EventType.PERSIST ) ) {
				listener.onPersist( event );
			}
		}
		catch (MappingException e) {
			throw exceptionConverter.convert( new IllegalArgumentException( e.getMessage() ) );
		}
		catch (RuntimeException e) {
			throw exceptionConverter.convert( e );
		}
		finally {
			try {
				checkNoUnresolvedActionsAfterOperation();
			}
			catch (RuntimeException e) {
				throw exceptionConverter.convert( e );
			}
		}
	}
