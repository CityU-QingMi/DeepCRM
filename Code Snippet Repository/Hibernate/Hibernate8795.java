	public static EnumUserType createInstance(Class clazz) {
		if ( !clazz.isEnum() ) {
			throw new IllegalArgumentException( "Parameter has to be an enum-class" );
		}
		EnumUserType that = new EnumUserType();
		Properties p = new Properties();
		p.setProperty( "enumClassName", clazz.getName() );
		that.setParameterValues( p );
		return that;
	}
