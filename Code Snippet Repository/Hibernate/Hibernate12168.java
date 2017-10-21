	public static String getPropertyName(String name) {
		String tmp = name;
		if ( name.startsWith( PROPERTY_PREFIX_GET ) ) {
			tmp = name.replaceFirst( PROPERTY_PREFIX_GET, "" );
		}
		else if ( name.startsWith( PROPERTY_PREFIX_IS ) ) {
			tmp = name.replaceFirst( PROPERTY_PREFIX_IS, "" );
		}
		else if ( name.startsWith( PROPERTY_PREFIX_HAS ) ) {
			tmp = name.replaceFirst( PROPERTY_PREFIX_HAS, "" );
		}
		return decapitalize( tmp );
	}
