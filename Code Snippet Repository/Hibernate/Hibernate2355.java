	private static boolean parenthesesMatch(String string) {
		int parenCount = 0;

		for ( int i = 0; i < string.length(); i++ ) {
			char character = string.charAt( i );

			if ( character == '(' ) {
				parenCount++;
			}
			else if ( character == ')' ) {
				parenCount--;
			}
		}

		return parenCount == 0;
	}
