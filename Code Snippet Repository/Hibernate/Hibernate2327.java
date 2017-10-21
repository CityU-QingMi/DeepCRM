	public static ExecuteUpdateResultCheckStyle fromExternalName(String name) {
		if ( name.equalsIgnoreCase( NONE.name ) ) {
			return NONE;
		}
		else if ( name.equalsIgnoreCase( COUNT.name ) ) {
			return COUNT;
		}
		else if ( name.equalsIgnoreCase( PARAM.name ) ) {
			return PARAM;
		}
		else {
			return null;
		}
	}
