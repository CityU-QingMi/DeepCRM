	public static PhysicalConnectionHandlingMode interpret(Object setting) {
		if ( setting == null ) {
			return null;
		}

		if ( setting instanceof PhysicalConnectionHandlingMode ) {
			return (PhysicalConnectionHandlingMode) setting;
		}

		final String value = setting.toString();
		if ( StringHelper.isEmpty( value ) ) {
			return null;
		}

		return PhysicalConnectionHandlingMode.valueOf( value.toUpperCase( Locale.ROOT ) );
	}
