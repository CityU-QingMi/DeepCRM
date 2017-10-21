	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if ( value == null || defaultValue.equals( value ) ) {
			log.trace( "binding null to parameter: " + index );
			st.setNull( index, Types.INTEGER );
		}
		else {
			log.trace( "binding " + value + " to parameter: " + index );
			st.setInt( index, ( (Integer) value ).intValue() );
		}
	}
