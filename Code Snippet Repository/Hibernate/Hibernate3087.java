	private void fireMerge(Map copiedAlready, MergeEvent event) {
		try {
			checkTransactionSynchStatus();
			for ( MergeEventListener listener : listeners( EventType.MERGE ) ) {
				listener.onMerge( event, copiedAlready );
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
