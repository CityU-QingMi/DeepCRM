	@Override
	public boolean isModified(
			final Object old,
			final Object current,
			final boolean[] checkable,
			final SharedSessionContractImplementor session) throws HibernateException {
		if ( old == current ) {
			return false;
		}
		// null value and empty components are considered equivalent
		int loc = 0;
		for ( int i = 0; i < propertySpan; i++ ) {
			int len = propertyTypes[i].getColumnSpan( session.getFactory() );
			boolean[] subcheckable = new boolean[len];
			System.arraycopy( checkable, loc, subcheckable, 0, len );
			if ( propertyTypes[i].isModified( getPropertyValue( old, i ), getPropertyValue( current, i ), subcheckable, session ) ) {
				return true;
			}
			loc += len;
		}
		return false;

	}
