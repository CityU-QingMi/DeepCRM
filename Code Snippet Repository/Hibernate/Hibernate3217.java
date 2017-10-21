	public static Field findField(Class containerClass, String propertyName) {
		if ( containerClass == null ) {
			throw new IllegalArgumentException( "Class on which to find field [" + propertyName + "] cannot be null" );
		}
		else if ( containerClass == Object.class ) {
			throw new IllegalArgumentException( "Illegal attempt to locate field [" + propertyName + "] on Object.class" );
		}

		Field field = locateField( containerClass, propertyName );

		if ( field == null ) {
			throw new PropertyNotFoundException(
					String.format(
							Locale.ROOT,
							"Could not locate field name [%s] on class [%s]",
							propertyName,
							containerClass.getName()
					)
			);
		}

		field.setAccessible( true );
		return field;
	}
