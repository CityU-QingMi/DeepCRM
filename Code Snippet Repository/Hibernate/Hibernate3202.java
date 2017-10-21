	private static Method getGetterOrNull(Class[] interfaces, String propertyName) {
		Method getter = null;
		for ( int i = 0; getter == null && i < interfaces.length; ++i ) {
			final Class anInterface = interfaces[i];
			getter = getGetterOrNull( anInterface, propertyName );
			if ( getter == null ) {
				// if no getter found yet, check all implemented interfaces of interface
				getter = getGetterOrNull( anInterface.getInterfaces(), propertyName );
			}
		}
		return getter;
	}
