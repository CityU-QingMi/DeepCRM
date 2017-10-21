	@Override
	public int getHashCode(final Object x, final SessionFactoryImplementor factory) {
		int result = 17;
		for ( int i = 0; i < propertySpan; i++ ) {
			Object y = getPropertyValue( x, i );
			result *= 37;
			if ( y != null ) {
				result += propertyTypes[i].getHashCode( y, factory );
			}
		}
		return result;
	}
