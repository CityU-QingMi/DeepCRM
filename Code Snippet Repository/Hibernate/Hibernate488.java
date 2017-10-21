	public static ConnectionAcquisitionMode interpret(Object setting) {
		if ( setting == null ) {
			return null;
		}

		if ( setting instanceof ConnectionAcquisitionMode ) {
			return (ConnectionAcquisitionMode) setting;
		}

		final String value = setting.toString();
		if ( StringHelper.isEmpty( value ) ) {
			return null;
		}

		return interpret( value );
	}
