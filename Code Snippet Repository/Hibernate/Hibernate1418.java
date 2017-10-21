	public Integer getInteger(String query, String hintName) {
		String value = (String) hintsMap.get( hintName );
		if ( value == null ) {
			return null;
		}
		try {
			return Integer.decode( value );
		}
		catch ( NumberFormatException nfe ) {
			throw new AnnotationException( "Not an integer in hint: " + query + ":" + hintName, nfe );
		}
	}
