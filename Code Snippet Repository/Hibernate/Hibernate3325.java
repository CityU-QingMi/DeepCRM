	public static int getInt(String name, Map values, int defaultValue) {
		Object value = values.get( name );
		if ( value == null ) {
			return defaultValue;
		}
		if ( Integer.class.isInstance( value ) ) {
			return (Integer) value;
		}
		if ( String.class.isInstance( value ) ) {
			return Integer.parseInt( (String) value );
		}
		throw new ConfigurationException(
				"Could not determine how to handle configuration value [name=" + name +
						", value=" + value + "(" + value.getClass().getName() + ")] as int"
		);
	}
