	public static FlushMode fromXml(String name) {
		// valid values are a subset of all FlushMode possibilities, so we will
		// handle the conversion here directly.
		// Also, we want to map "never"->MANUAL (rather than NEVER)
		if ( name == null ) {
			return null;
		}

		if ( "never".equalsIgnoreCase( name ) ) {
			return FlushMode.MANUAL;
		}
		else if ( "auto".equalsIgnoreCase( name ) ) {
			return FlushMode.AUTO;
		}
		else if ( "always".equalsIgnoreCase( name ) ) {
			return FlushMode.ALWAYS;
		}

		// if the incoming value was not null *and* was not one of the pre-defined
		// values, we need to throw an exception.  This *should never happen if the
		// document we are processing conforms to the schema...
		throw new HibernateException( "Unrecognized flush mode : " + name );
	}
