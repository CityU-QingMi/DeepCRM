	private void addEventListeners(Map<EventType, Set<String>> eventListenerMap) {
		if ( eventListenerMap == null ) {
			return;
		}

		if ( this.eventListenerMap == null ) {
			this.eventListenerMap = new HashMap<EventType, Set<String>>();
		}

		for ( Map.Entry<EventType, Set<String>> incomingEntry : eventListenerMap.entrySet() ) {
			Set<String> listenerClasses = this.eventListenerMap.get( incomingEntry.getKey() );
			if ( listenerClasses == null ) {
				listenerClasses = new HashSet<String>();
				this.eventListenerMap.put( incomingEntry.getKey(), listenerClasses );
			}
			listenerClasses.addAll( incomingEntry.getValue() );
		}
	}
