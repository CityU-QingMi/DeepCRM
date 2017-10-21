	@SuppressWarnings("")
	public static boolean equals(final Object x, final Object y) {
		if ( x == y ) {
			return true;
		}

		if ( x == null || y == null ) {
			// One is null, but the other is not (otherwise the `x == y` check would have passed).
			// null can never equal a non-null
			return false;
		}

		return x.equals( y );
	}
