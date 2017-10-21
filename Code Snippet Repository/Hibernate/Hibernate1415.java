	public FlushMode getFlushMode(String query) {
		String hitName = QueryHints.FLUSH_MODE;
		String value =(String)  hintsMap.get( hitName );
		if ( value == null ) {
			return null;
		}
		try {
			return FlushMode.interpretExternalSetting( value );
		}
		catch ( MappingException e ) {
			throw new AnnotationException( "Unknown FlushMode in hint: " + query + ":" + hitName, e );
		}
	}
