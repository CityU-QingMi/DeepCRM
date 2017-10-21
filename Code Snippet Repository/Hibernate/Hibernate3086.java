	private Object fireMerge(MergeEvent event) {
		try {
			checkTransactionSynchStatus();
			checkNoUnresolvedActionsBeforeOperation();
			for ( MergeEventListener listener : listeners( EventType.MERGE ) ) {
				listener.onMerge( event );
			}
			checkNoUnresolvedActionsAfterOperation();
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

		return event.getResult();
	}
