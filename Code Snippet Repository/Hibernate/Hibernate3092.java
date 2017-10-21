	private void fireDelete(DeleteEvent event, Set transientEntities) {
		try{
		checkTransactionSynchStatus();
		for ( DeleteEventListener listener : listeners( EventType.DELETE ) ) {
			listener.onDelete( event, transientEntities );
		}
		}
		catch ( ObjectDeletedException sse ) {
			throw exceptionConverter.convert( new IllegalArgumentException( sse ) );
		}
		catch ( MappingException e ) {
			throw exceptionConverter.convert( new IllegalArgumentException( e.getMessage(), e ) );
		}
		catch ( RuntimeException e ) {
			//including HibernateException
			throw exceptionConverter.convert( e );
		}
		finally {
			delayedAfterCompletion();
		}
	}
