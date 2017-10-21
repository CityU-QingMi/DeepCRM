	@Override
	@SuppressWarnings("")
	public String toString(Object value) throws HibernateException {
		if ( StringRepresentableType.class.isInstance( userType ) ) {
			return ( (StringRepresentableType) userType ).toString( value );
		}
		if ( value == null ) {
			return null;
		}
		if ( EnhancedUserType.class.isInstance( userType ) ) {
			//noinspection deprecation
			return ( (EnhancedUserType) userType ).toXMLString( value );
		}
		return value.toString();
	}
