	public static char getLastNonWhitespaceCharacter(String str) {
		if ( str != null && str.length() > 0 ) {
			for ( int i = str.length() - 1; i >= 0; i-- ) {
				char ch = str.charAt( i );
				if ( !Character.isWhitespace( ch ) ) {
					return ch;
				}
			}
		}
		return '\0';
	}
