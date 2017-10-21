	public static MetadataSourceType parsePrecedence(String value) {
		if ( HBM.name.equalsIgnoreCase( value ) ) {
			return HBM;
		}

		if ( CLASS.name.equalsIgnoreCase( value ) ) {
			return CLASS;
		}

		throw new HibernateException( "Unknown metadata source type value [" + value + "]" );
	}
