	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
		String result = rs.getString( names[0] );
		if ( rs.wasNull() ) return null;
		if ( parameters.getProperty( CAST ).equals( "lower" ) ) {
			return result.toLowerCase(Locale.ROOT);
		}
		else {
			return result.toUpperCase(Locale.ROOT);
		}
	}
