	public static String getString(String name, Map values, String defaultValue, String ... otherSupportedValues) {
		final String value = getString( name, values, defaultValue );
		if ( !defaultValue.equals( value ) && ArrayHelper.indexOf( otherSupportedValues, value ) == -1 ) {
			throw new ConfigurationException(
					"Unsupported configuration [name=" + name + ", value=" + value + "]. " +
							"Choose value between: '" + defaultValue + "', '" + StringHelper.join( "', '", otherSupportedValues ) + "'."
			);
		}
		return value;
	}
