	public static ConnectionReleaseMode interpret(Object setting) {
		if ( setting == null ) {
			return null;
		}

		if ( setting instanceof ConnectionReleaseMode ) {
			return (ConnectionReleaseMode) setting;
		}

		final String value = setting.toString();
		if ( StringHelper.isEmpty( value ) ) {
			return null;
		}

		// here we disregard "auto"
		if ( value.equalsIgnoreCase( "auto" ) ) {
			return null;
		}

		return parse( value );
	}
