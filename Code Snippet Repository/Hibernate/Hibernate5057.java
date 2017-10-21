	@Override
	public boolean isSame(Object x, Object y) throws HibernateException {
		if ( x == y ) {
			return true;
		}
		// null value and empty component are considered equivalent
		Object[] xvalues = getPropertyValues( x, entityMode );
		Object[] yvalues = getPropertyValues( y, entityMode );
		for ( int i = 0; i < propertySpan; i++ ) {
			if ( !propertyTypes[i].isSame( xvalues[i], yvalues[i] ) ) {
				return false;
			}
		}
		return true;
	}
