	public void nullSafeSet(
			final PreparedStatement st, final Object value,
			final int index, final SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		if ( value == null ) {
			st.setNull( index, StringType.INSTANCE.sqlType() );
			st.setNull( index + 1, IntegerType.INSTANCE.sqlType() );
		}
		else {
			final Component comp = (Component) value;
			st.setString( index, comp.getProp1() );
			st.setInt( index + 1, comp.getProp2() );
		}
	}
