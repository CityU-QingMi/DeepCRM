	private static Field locateField(Class clazz, String propertyName) {
		if ( clazz == null || Object.class.equals( clazz ) ) {
			return null;
		}

		try {
			return clazz.getDeclaredField( propertyName );
		}
		catch ( NoSuchFieldException nsfe ) {
			return locateField( clazz.getSuperclass(), propertyName );
		}
	}
