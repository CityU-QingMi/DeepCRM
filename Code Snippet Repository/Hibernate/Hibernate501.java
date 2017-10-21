	public static NullPrecedence parse(String name) {
		if ( "none".equalsIgnoreCase( name ) ) {
			return NullPrecedence.NONE;
		}
		else if ( "first".equalsIgnoreCase( name ) ) {
			return NullPrecedence.FIRST;
		}
		else if ( "last".equalsIgnoreCase( name ) ) {
			return NullPrecedence.LAST;
		}
		return null;
	}
