	public static ResultColumnReferenceStrategy resolveByName(String name) {
		if ( ALIAS.name().equalsIgnoreCase( name ) ) {
			return ALIAS;
		}
		else if ( ORDINAL.name().equalsIgnoreCase( name ) ) {
			return ORDINAL;
		}
		else {
			return SOURCE;
		}
	}
