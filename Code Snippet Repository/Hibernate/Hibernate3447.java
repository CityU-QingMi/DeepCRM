	private static boolean isValidNumber(final String parameterName) {
		if ( parameterName.length() == 0 ) {
			return false;
		}
		final char firstDigit = parameterName.charAt( 0 );
		if ( Character.isDigit( firstDigit ) || '-' == firstDigit || '+' == firstDigit ) {
			//check the remaining characters
			for ( int i = 1; i < parameterName.length(); i++ ) {
				if ( !Character.isDigit( parameterName.charAt( i ) ) ) {
					return false;
				}
			}
			//Some edge cases are left open: just a sign would return true.
			//For those cases you'd have a NumberFormatException swallowed.
			return true;
		}
		return false;
	}
