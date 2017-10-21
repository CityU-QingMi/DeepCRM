	public boolean isDirty(final Object x, final Object y, final boolean[] checkable, final SharedSessionContractImplementor session)
			throws HibernateException {
		if ( x == y ) {
			return false;
		}
		// null value and empty component are considered equivalent
		int loc = 0;
		for ( int i = 0; i < propertySpan; i++ ) {
			int len = propertyTypes[i].getColumnSpan( session.getFactory() );
			if ( len <= 1 ) {
				final boolean dirty = ( len == 0 || checkable[loc] ) &&
						propertyTypes[i].isDirty( getPropertyValue( x, i ), getPropertyValue( y, i ), session );
				if ( dirty ) {
					return true;
				}
			}
			else {
				boolean[] subcheckable = new boolean[len];
				System.arraycopy( checkable, loc, subcheckable, 0, len );
				final boolean dirty = propertyTypes[i].isDirty(
						getPropertyValue( x, i ),
						getPropertyValue( y, i ),
						subcheckable,
						session
				);
				if ( dirty ) {
					return true;
				}
			}
			loc += len;
		}
		return false;
	}
