	public void addEventListener(EventType eventType, String listenerClass) {
		if ( eventListenerMap == null ) {
			eventListenerMap = new HashMap<EventType, Set<String>>();
		}

		Set<String> listenerClasses = eventListenerMap.get( eventType );
		if ( listenerClasses == null ) {
			listenerClasses = new HashSet<String>();
			eventListenerMap.put( eventType, listenerClasses );
		}

		listenerClasses.add( listenerClass );
	}
