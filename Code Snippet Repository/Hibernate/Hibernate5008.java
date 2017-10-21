	@Override
	public int compare(Object x, Object y) {
		if ( x == null ) {
			// if y is also null, return that they are the same (no option for "UNKNOWN")
			// if y is not null, return that y is "greater" (-1 because the result is from the perspective of
			// 		the first arg: x)
			return y == null ? 0 : -1;
		}
		else if ( y == null ) {
			// x is not null, but y is.  return that x is "greater"
			return 1;
		}

		// At this point we know both are non-null.
		final Object xId = extractIdentifier( x );
		final Object yId = extractIdentifier( y );

		return getIdentifierType().compare( xId, yId );
	}
