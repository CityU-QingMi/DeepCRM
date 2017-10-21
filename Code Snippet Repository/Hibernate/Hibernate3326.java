	public static Integer getInteger(String name, Map values) {
		Object value = values.get( name );
		if ( value == null ) {
			return null;
		}
		if ( Integer.class.isInstance( value ) ) {
			return (Integer) value;
		}
		if ( String.class.isInstance( value ) ) {
			//empty values are ignored
			final String trimmed = value.toString().trim();
			if ( trimmed.isEmpty() ) {
				return null;
			}
			return Integer.valueOf( trimmed );
		}
		throw new ConfigurationException(
				"Could not determine how to handle configuration value [name=" + name +
						", value=" + value + "(" + value.getClass().getName() + ")] as Integer"
		);
	}
