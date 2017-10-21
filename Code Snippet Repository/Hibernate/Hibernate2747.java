	public String getTokenTypeName(int type) {
		final Integer typeInteger = type;
		String value = null;
		if ( tokenTypeNameCache != null ) {
			value = (String) tokenTypeNameCache.get( typeInteger );
		}
		if ( value == null ) {
			value = typeInteger.toString();
		}
		return value;
	}
