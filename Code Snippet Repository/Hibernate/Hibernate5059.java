	@Override
	public boolean isEqual(final Object x, final Object y, final SessionFactoryImplementor factory)
			throws HibernateException {
		if ( x == y ) {
			return true;
		}
		// null value and empty component are considered equivalent
		for ( int i = 0; i < propertySpan; i++ ) {
			if ( !propertyTypes[i].isEqual( getPropertyValue( x, i ), getPropertyValue( y, i ), factory ) ) {
				return false;
			}
		}
		return true;
	}
