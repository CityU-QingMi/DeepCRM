	private static Iterable<PersistEventListener> persistEventListeners(SharedSessionContractImplementor session) {
		if ( session == null ) {
			return Collections.emptyList();
		}
		return session
				.getFactory()
				.getServiceRegistry()
				.getService( EventListenerRegistry.class )
				.getEventListenerGroup( EventType.PERSIST )
				.listeners();
	}
