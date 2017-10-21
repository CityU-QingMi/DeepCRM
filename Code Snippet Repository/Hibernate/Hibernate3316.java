	public static String getString(String name, Map values) {
		Object value = values.get( name );
		if ( value == null ) {
			return null;
		}
		if ( String.class.isInstance( value ) ) {
			return (String) value;
		}
		return value.toString();
	}
