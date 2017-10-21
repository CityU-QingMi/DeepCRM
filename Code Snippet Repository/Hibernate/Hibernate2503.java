	private static <T> void prepareListeners(EventType<T> type, T defaultListener, EventListenerGroupImpl[] listenerArray) {
		final EventListenerGroupImpl<T> listenerGroup;
		if ( type == EventType.POST_COMMIT_DELETE
				|| type == EventType.POST_COMMIT_INSERT
				|| type == EventType.POST_COMMIT_UPDATE ) {
			listenerGroup = new PostCommitEventListenerGroupImpl<T>( type );
		}
		else {
			listenerGroup = new EventListenerGroupImpl<T>( type );
		}

		if ( defaultListener != null ) {
			listenerGroup.appendListener( defaultListener );
		}
		listenerArray[ type.ordinal() ] = listenerGroup;
	}
