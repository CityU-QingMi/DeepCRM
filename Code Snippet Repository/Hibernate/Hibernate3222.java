	public static int lastIndexOfLetter(String string) {
		for ( int i = 0; i < string.length(); i++ ) {
			char character = string.charAt( i );
			// Include "_".  See HHH-8073
			if ( !Character.isLetter( character ) && !( '_' == character ) ) {
				return i - 1;
			}
		}
		return string.length() - 1;
	}
