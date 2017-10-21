	public Object get(ResultSet rs, String name) throws SQLException {
		final String value = rs.getString( name );
		if ( rs.wasNull() ) {
			return getDefaultValue();
		}
		else if ( "TRUE".equalsIgnoreCase( value ) ) {
			return Boolean.TRUE;
		}
		else {
			return Boolean.FALSE;
		}
	}
