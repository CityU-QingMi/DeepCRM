	@Override
	@SuppressWarnings("")
	public <T> Listener<T> buildListener(Class<T> listenerClass) {
		ListenerImpl listenerImpl = listenerMap.get( listenerClass );
		if ( listenerImpl == null ) {
			listenerImpl = new ListenerImpl( listenerClass );
			listenerMap.put( listenerClass, listenerImpl );
		}
		return (Listener<T>) listenerImpl;
	}
