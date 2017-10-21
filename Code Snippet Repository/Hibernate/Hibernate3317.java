	public static String extractPropertyValue(String propertyName, Map properties) {
		String value = (String) properties.get( propertyName );
		if ( value == null ) {
			return null;
		}
		value = value.trim();
		if ( StringHelper.isEmpty( value ) ) {
			return null;
		}
		return value;
	}
