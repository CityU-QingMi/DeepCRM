	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
		if ( value == null ) {
			st.setNull( index, sqlTypes()[0] );
			return;
		}
		
		PhoneNumber phoneNumber = (PhoneNumber) value;
		String number = phoneNumber.getNumber();
		st.setString( index, number);
	}
