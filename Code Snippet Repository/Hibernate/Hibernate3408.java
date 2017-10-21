	@Override
	@SuppressWarnings("")
	public <T> Listener<T> buildListener(Class<T> listenerClass) {
		ListenerImpl listenerImpl = listenerInstances.get( listenerClass );
		if ( listenerImpl == null ) {
			try {
				T listenerInstance = listenerClass.newInstance();
				listenerImpl = new ListenerImpl( listenerInstance );
			}
			catch (Exception e) {
				throw new PersistenceException(
						"Unable to create instance of " + listenerClass.getName() + " as a JPA callback listener",
						e
				);
			}
			ListenerImpl existing = listenerInstances.putIfAbsent(
					listenerClass,
					listenerImpl
			);
			if ( existing != null ) {
				listenerImpl = existing;
			}
		}
		return (Listener<T>) listenerImpl;
	}
