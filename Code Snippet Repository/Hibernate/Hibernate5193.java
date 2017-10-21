	public Class fromString(String string) {
		if ( string == null ) {
			return null;
		}

		try {
			return ReflectHelper.classForName( string );
		}
		catch ( ClassNotFoundException e ) {
			throw new HibernateException( "Unable to locate named class " + string );
		}
	}
