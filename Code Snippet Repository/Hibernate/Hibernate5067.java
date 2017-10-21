	@Override
	public String toLoggableString(Object value, SessionFactoryImplementor factory) throws HibernateException {
		if ( value == null ) {
			return "null";
		}
		else if ( customLogging ) {
			return ( (LoggableUserType) userType ).toLoggableString( value, factory );
		}
		else {
			return value.toString();
		}
	}
