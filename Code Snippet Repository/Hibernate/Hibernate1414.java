	public CacheMode getCacheMode(String query) {
		String hitName = QueryHints.CACHE_MODE;
		String value =(String) hintsMap.get( hitName );
		if ( value == null ) {
			return null;
		}
		try {
			return CacheMode.interpretExternalSetting( value );
		}
		catch ( MappingException e ) {
			throw new AnnotationException( "Unknown CacheMode in hint: " + query + ":" + hitName, e );
		}
	}
