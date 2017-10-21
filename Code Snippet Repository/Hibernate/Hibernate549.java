	private boolean preInsert() {
		final EventListenerGroup<PreInsertEventListener> listenerGroup = listenerGroup( EventType.PRE_INSERT );
		if ( listenerGroup.isEmpty() ) {
			// NO_VETO
			return false;
		}
		boolean veto = false;
		final PreInsertEvent event = new PreInsertEvent( getInstance(), null, getState(), getPersister(), eventSource() );
		for ( PreInsertEventListener listener : listenerGroup.listeners() ) {
			veto |= listener.onPreInsert( event );
		}
		return veto;
	}
