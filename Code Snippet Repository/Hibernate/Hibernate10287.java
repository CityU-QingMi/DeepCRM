	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		CustomEnum val = (CustomEnum) value;
		if ( val == null ) {
			st.setNull( index, Types.VARCHAR );
		}
		else {
			st.setString( index, val.toYesNo() );
		}
	}
