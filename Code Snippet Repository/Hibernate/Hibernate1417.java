	public boolean getBoolean(String query, String hintName) {
		String value =(String)  hintsMap.get( hintName );
		if ( value == null ) {
			return false;
		}
		if ( value.equalsIgnoreCase( "true" ) ) {
			return true;
		}
		else if ( value.equalsIgnoreCase( "false" ) ) {
			return false;
		}
		else {
			throw new AnnotationException( "Not a boolean in hint: " + query + ":" + hintName );
		}

	}
