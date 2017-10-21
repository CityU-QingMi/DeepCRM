	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int begin, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {

		Object[] subvalues = nullSafeGetValues( value, entityMode );

		for ( int i = 0; i < propertySpan; i++ ) {
			propertyTypes[i].nullSafeSet( st, subvalues[i], begin, session );
			begin += propertyTypes[i].getColumnSpan( session.getFactory() );
		}
	}
