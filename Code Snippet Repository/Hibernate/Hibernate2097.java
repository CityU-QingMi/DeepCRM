	public static Properties getConnectionProperties(Map<?,?> properties) {
		final Properties result = new Properties();
		for ( Map.Entry entry : properties.entrySet() ) {
			if ( ! ( String.class.isInstance( entry.getKey() ) ) || ! String.class.isInstance( entry.getValue() ) ) {
				continue;
			}
			final String key = (String) entry.getKey();
			final String value = (String) entry.getValue();
			if ( key.startsWith( AvailableSettings.CONNECTION_PREFIX ) ) {
				if ( SPECIAL_PROPERTIES.contains( key ) ) {
					if ( AvailableSettings.USER.equals( key ) ) {
						result.setProperty( "user", value );
					}
				}
				else {
					result.setProperty(
							key.substring( AvailableSettings.CONNECTION_PREFIX.length() + 1 ),
							value
					);
				}
			}
			else if ( CONDITIONAL_PROPERTIES.containsKey( key ) ) {
				result.setProperty( CONDITIONAL_PROPERTIES.get( key ), value );
			}
		}
		return result;
	}
