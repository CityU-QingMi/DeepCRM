	@Override
	public boolean isDirty(final Object x, final Object y, final SharedSessionContractImplementor session) throws HibernateException {
		if ( x == y ) {
			return false;
		}
		// null value and empty component are considered equivalent
		for ( int i = 0; i < propertySpan; i++ ) {
			if ( propertyTypes[i].isDirty( getPropertyValue( x, i ), getPropertyValue( y, i ), session ) ) {
				return true;
			}
		}
		return false;
	}
