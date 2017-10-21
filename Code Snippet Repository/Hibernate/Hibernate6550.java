	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
		String result = rs.getString( names[0] );
		if ( rs.wasNull() ) return null;
		
		if (result.length() <= 6) {
			return new PhoneNumber(result);
		}
		else {
			return new OverseasPhoneNumber(result);
		}
	}
