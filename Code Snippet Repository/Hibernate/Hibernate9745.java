	public static Object getBeanProperty(Object bean, String propertyName) {
		validateArgs( bean, propertyName );

		// try getters first
		final Method getter = getMethod( bean, propertyName );
		if ( getter != null ) {
			try {
				return getter.invoke( bean );
			}
			catch (Exception e) {
				/**/
			}
		}

		// then try fields
		final Field field = getField( bean, propertyName );
		if ( field != null ) {
			try {
				field.setAccessible( true );
				return field.get( bean );
			}
			catch (Exception e) {
				/**/
			}
		}

		return null;
	}
