	protected void checkIncomingPropertyName(String propertyName) {
		if ( propertyName == null ) {
			throw new NullPointerException( "Provided property name cannot be null" );
		}

		//if ( propertyName.contains( "." ) ) {
		//	throw new IllegalArgumentException(
		//			"Provided property name cannot contain paths (dots) [" + propertyName + "]"
		//	);
		//}
	}
