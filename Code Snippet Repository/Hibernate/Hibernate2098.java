	public static Integer interpretIsolation(Object setting) {
		if ( setting == null ) {
			return null;
		}

		if ( Number.class.isInstance( setting ) ) {
			return ( (Number) setting ).intValue();
		}

		final String settingAsString = setting.toString();
		if ( StringHelper.isEmpty( settingAsString ) ) {
			return null;
		}

		if ( ISOLATION_VALUE_MAP.containsKey( settingAsString ) ) {
			return ISOLATION_VALUE_MAP.get( settingAsString );
		}

		// it could be a String representation of the isolation numeric value...
		try {
			return Integer.valueOf( settingAsString );
		}
		catch (NumberFormatException ignore) {
		}

		throw new HibernateException( "Could not interpret transaction isolation setting [" + setting + "]" );
	}
