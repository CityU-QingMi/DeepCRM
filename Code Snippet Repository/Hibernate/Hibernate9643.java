	public static Field getField(Class clazz, String name) {
		try {
			Field field = clazz.getDeclaredField( name );
			field.setAccessible( true );
			return field;
		}
		catch ( NoSuchFieldException e ) {
			throw new IllegalArgumentException( "Class " + clazz + " does not contain a " + name + " field", e);
		}
	}
