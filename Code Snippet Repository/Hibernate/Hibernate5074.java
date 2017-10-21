	@Override
	public Object fromStringValue(String string) throws HibernateException {
		if ( StringRepresentableType.class.isInstance( userType ) ) {
			return ( (StringRepresentableType) userType ).fromStringValue( string );
		}
		if ( EnhancedUserType.class.isInstance( userType ) ) {
			//noinspection deprecation
			return ( (EnhancedUserType) userType ).fromXMLString( string );
		}
		throw new HibernateException(
				String.format(
						"Could not process #fromStringValue, UserType class [%s] did not implement %s or %s",
						name,
						StringRepresentableType.class.getName(),
						EnhancedUserType.class.getName()
				)
		);
	}
