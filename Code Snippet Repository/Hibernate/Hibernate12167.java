	public static boolean isProperty(String methodName, String returnTypeAsString) {
		if ( methodName == null || "void".equals( returnTypeAsString ) ) {
			return false;
		}

		if ( isValidPropertyName( methodName, PROPERTY_PREFIX_GET ) ) {
			return true;
		}

		if ( isValidPropertyName( methodName, PROPERTY_PREFIX_IS ) || isValidPropertyName( methodName, PROPERTY_PREFIX_HAS ) ) {
			return isBooleanGetter( returnTypeAsString );
		}

		return false;
	}
