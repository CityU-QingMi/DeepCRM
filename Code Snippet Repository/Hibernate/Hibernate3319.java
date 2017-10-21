	public static Map toMap(String propertyName, String delim, Map properties) {
		Map map = new HashMap();
		String value = extractPropertyValue( propertyName, properties );
		if ( value != null ) {
			StringTokenizer tokens = new StringTokenizer( value, delim );
			while ( tokens.hasMoreTokens() ) {
				map.put( tokens.nextToken(), tokens.hasMoreElements() ? tokens.nextToken() : "" );
			}
		}
		return map;
	}
