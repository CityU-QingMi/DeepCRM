	public PostCommitEventListenerGroupImpl(EventType<T> eventType) {
		super( eventType );

		if ( eventType == EventType.POST_COMMIT_DELETE ) {
			this.extendedListenerContract = PostCommitDeleteEventListener.class;
		}
		else if ( eventType == EventType.POST_COMMIT_INSERT ) {
			this.extendedListenerContract = PostCommitInsertEventListener.class;
		}
		else if ( eventType == EventType.POST_COMMIT_UPDATE ) {
			this.extendedListenerContract = PostCommitUpdateEventListener.class;
		}
		else {
			throw new IllegalStateException( "Unexpected usage of PostCommitEventListenerGroupImpl" );
		}
	}
