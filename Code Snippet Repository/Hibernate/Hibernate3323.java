	public static boolean getBoolean(String name, Map values, boolean defaultValue) {
		Object value = values.get( name );
		if ( value == null ) {
			return defaultValue;
		}
		if ( Boolean.class.isInstance( value ) ) {
			return ( (Boolean) value ).booleanValue();
		}
		if ( String.class.isInstance( value ) ) {
			return Boolean.parseBoolean( (String) value );
		}
		throw new ConfigurationException(
				"Could not determine how to handle configuration value [name=" + name + ", value=" + value + "] as boolean"
		);
	}
