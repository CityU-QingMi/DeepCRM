	@Override
	public int compare(final Object x, final Object y) {
		if ( x == y ) {
			return 0;
		}
		for ( int i = 0; i < propertySpan; i++ ) {
			int propertyCompare = propertyTypes[i].compare( getPropertyValue( x, i ), getPropertyValue( y, i ) );
			if ( propertyCompare != 0 ) {
				return propertyCompare;
			}
		}
		return 0;
	}
