	public void set(PreparedStatement st, Boolean value, int index)
			throws SQLException {

		if ( value == null ) {
			st.setNull( index, Types.VARCHAR );
		}
		else {
			st.setString( index, value ? "TRUE" : "FALSE" );
		}
	}
