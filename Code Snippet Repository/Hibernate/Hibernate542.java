	private void postCommitDelete(boolean success) {
		final EventListenerGroup<PostDeleteEventListener> listenerGroup = listenerGroup( EventType.POST_COMMIT_DELETE );
		if ( listenerGroup.isEmpty() ) {
			return;
		}
		final PostDeleteEvent event = new PostDeleteEvent(
				getInstance(),
				getId(),
				state,
				getPersister(),
				eventSource()
		);
		for ( PostDeleteEventListener listener : listenerGroup.listeners() ) {
			if ( PostCommitDeleteEventListener.class.isInstance( listener ) ) {
				if ( success ) {
					listener.onPostDelete( event );
				}
				else {
					((PostCommitDeleteEventListener) listener).onPostDeleteCommitFailed( event );
				}
			}
			else {
				//default to the legacy implementation that always fires the event
				listener.onPostDelete( event );
			}
		}
	}
