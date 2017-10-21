	private Getter getter(String property) {
		try {
			Field field = Target.class.getDeclaredField( property );
			field.setAccessible( true );
			return new GetterFieldImpl( Target.class, property, field );
		}
		catch (NoSuchFieldException e) {
			throw new IllegalArgumentException( e );
		}
	}
