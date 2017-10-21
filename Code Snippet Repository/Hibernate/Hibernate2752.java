	public static String getTokenTypeName(Class tokenTypeInterface, int tokenType) {
		String tokenTypeName = Integer.toString( tokenType );
		if ( tokenTypeInterface != null ) {
			Field[] fields = tokenTypeInterface.getFields();
			for ( Field field : fields ) {
				final Integer fieldValue = extractIntegerValue( field );
				if ( fieldValue != null && fieldValue == tokenType ) {
					tokenTypeName = field.getName();
					break;
				}
			}
		}
		return tokenTypeName;
	}
