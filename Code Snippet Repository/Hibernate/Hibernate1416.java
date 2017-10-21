	public LockMode getLockMode(String query) {
		String hitName = QueryHints.NATIVE_LOCKMODE;
		String value =(String) hintsMap.get( hitName );
		if ( value == null ) {
			return null;
		}
		try {
			return LockMode.fromExternalForm( value );
		}
		catch ( MappingException e ) {
			throw new AnnotationException( "Unknown LockMode in hint: " + query + ":" + hitName, e );
		}
	}
