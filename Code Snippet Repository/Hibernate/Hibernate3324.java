	public static Boolean getBooleanWrapper(String name, Map values, Boolean defaultValue) {
		Object value = values.get( name );
		if ( value == null ) {
			return defaultValue;
		}
		if ( Boolean.class.isInstance( value ) ) {
			return (Boolean) value;
		}
		if ( String.class.isInstance( value ) ) {
			return Boolean.valueOf( (String) value );
		}
		throw new ConfigurationException(
				"Could not determine how to handle configuration value [name=" + name + ", value=" + value + "] as boolean"
		);
	}
