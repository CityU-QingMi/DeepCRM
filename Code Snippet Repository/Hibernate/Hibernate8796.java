	public void setParameterValues(Properties params) {
		String enumClassName = params.getProperty( "enumClassName" );
		if ( enumClassName == null ) {
			throw new MappingException( "enumClassName parameter not specified" );
		}

		try {
			this.clazz = Class.forName( enumClassName );
		}
		catch (ClassNotFoundException e) {
			throw new MappingException( "enumClass " + enumClassName + " not found", e );
		}
		if ( !this.clazz.isEnum() ) {
			throw new MappingException( "enumClass " + enumClassName + " doesn't refer to an Enum" );
		}
	}
