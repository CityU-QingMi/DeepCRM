	public static String extractPropertyValue(String propertyName, Properties properties) {
		String value = properties.getProperty( propertyName );
		if ( value == null ) {
			return null;
		}
		value = value.trim();
		if ( StringHelper.isEmpty( value ) ) {
			return null;
		}
		return value;
	}
