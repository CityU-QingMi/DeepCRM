	@Override
	protected boolean hasPostCommitEventListeners() {
		final EventListenerGroup<PostDeleteEventListener> group = listenerGroup( EventType.POST_COMMIT_DELETE );
		for ( PostDeleteEventListener listener : group.listeners() ) {
			if ( listener.requiresPostCommitHandling( getPersister() ) ) {
				return true;
			}
		}

		return false;
	}
