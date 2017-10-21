	public static Properties toProperties(Map map) {
		if ( map == null ) {
			return null;
		}

		if ( map instanceof Properties ) {
			return (Properties) map;
		}

		Properties properties = new Properties();
		properties.putAll( map );
		return properties;
	}
