	public static char getFirstNonWhitespaceCharacter(String str) {
		if ( str != null && str.length() > 0 ) {
			for ( int i = 0; i < str.length(); i++ ) {
				char ch = str.charAt( i );
				if ( !Character.isWhitespace( ch ) ) {
					return ch;
				}
			}
		}
		return '\0';
	}
