	private static Method setterOrNull(Class[] interfaces, String propertyName, Class propertyType) {
		Method setter = null;
		for ( int i = 0; setter == null && i < interfaces.length; ++i ) {
			final Class anInterface = interfaces[i];
			setter = setterOrNull( anInterface, propertyName, propertyType );
			if ( setter == null ) {
				// if no setter found yet, check all implemented interfaces of interface
				setter = setterOrNull( anInterface.getInterfaces(), propertyName, propertyType );
			}
		}
		return setter;
	}
