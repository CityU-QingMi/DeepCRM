	public static <T> T getFieldValue(Object target, String name) {
		try {
			Field field = target.getClass().getDeclaredField( name );
			field.setAccessible( true );
			return (T) field.get( target );
		}
		catch ( NoSuchFieldException e ) {
			throw new IllegalArgumentException( "Class " + target.getClass() + " does not contain a " + name + " field", e);
		}
		catch ( IllegalAccessException e ) {
			throw new IllegalArgumentException( "Cannot set field " + name, e);
		}
	}
