	@Override
	public void registerCallbacks(Class entityClass, Callback[] callbacks) {
		if ( callbacks == null || callbacks.length == 0 ) {
			return;
		}

		final HashMap<Class, Callback[]> map = determineAppropriateCallbackMap( callbacks[0].getCallbackType() );
		if ( map.containsKey( entityClass ) ) {
			throw new PersistenceException( "Error build callback listeners; entity [" + entityClass.getName() + " was already processed" );
		}
		map.put( entityClass, callbacks );
	}
