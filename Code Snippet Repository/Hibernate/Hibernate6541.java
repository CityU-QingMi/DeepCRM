	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
		if ( value == null ) {
			st.setNull( index, sqlTypes()[0] );
		}
		else {
			String string = (String) value;
			if ( parameters.getProperty( CAST ).equals( "lower" ) ) {
				string = string.toLowerCase(Locale.ROOT);
			}
			else {
				string = string.toUpperCase(Locale.ROOT);
			}
			st.setString( index, string );
		}
	}
