	public static long getLong(String name, Map values, int defaultValue) {
		Object value = values.get( name );
		if ( value == null ) {
			return defaultValue;
		}
		if ( Long.class.isInstance( value ) ) {
			return (Long) value;
		}
		if ( String.class.isInstance( value ) ) {
			return Long.parseLong( (String) value );
		}
		throw new ConfigurationException(
				"Could not determine how to handle configuration value [name=" + name +
						", value=" + value + "(" + value.getClass().getName() + ")] as long"
		);
	}
