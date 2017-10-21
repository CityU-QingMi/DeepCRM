	public static <T> Constructor<T> getDefaultConstructor(Class<T> clazz) throws PropertyNotFoundException {
		if ( isAbstractClass( clazz ) ) {
			return null;
		}

		try {
			Constructor<T> constructor = clazz.getDeclaredConstructor( NO_PARAM_SIGNATURE );
			constructor.setAccessible( true );
			return constructor;
		}
		catch ( NoSuchMethodException nme ) {
			throw new PropertyNotFoundException(
					"Object class [" + clazz.getName() + "] must declare a default (no-argument) constructor"
			);
		}
	}
