	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
		if ( value == null ) {
			for (int i = 0; i < 6; i++ ) {
				st.setNull( index + i, Types.INTEGER );
			}
		} else {
			final CompositeDateTime dateTime = (CompositeDateTime) value;
			st.setObject( index, dateTime.getYear() );
			st.setObject( index + 1, dateTime.getMonth() );
			st.setObject( index + 2, dateTime.getDay() );
			st.setObject( index + 3, dateTime.getHour() );
			st.setObject( index + 4, dateTime.getMinute() );
			st.setObject( index + 5, dateTime.getSecond() );
		}
	}
