	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if ( value == null ) {
			st.setNull( index, TYPES[0] );
			st.setNull( index + 1, TYPES[1] );
		}
		else {
			st.setString( index, value.getClass().getName() );
			st.setBinaryStream( index + 1, convertObjectToInputStream( value ) );
		}
	}
