	public static Integer toNumberOrNull(final String parameterName) {
		if ( isValidNumber( parameterName ) ) {
			try {
				return Integer.valueOf( parameterName );
			}
			catch (NumberFormatException e) {
				//It wasn't valid after all, so return null
			}
		}
		return null;
	}
