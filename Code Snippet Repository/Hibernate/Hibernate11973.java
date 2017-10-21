	static public int getSRID(String wkt) {
		String[] tokens = wkt.split( ";" );
		if ( tokens.length == 1 ) {
			return 0;
		}
		String[] sridTokens = tokens[0].split( "=" );
		if ( sridTokens.length < 2 ) {
			throw new IllegalArgumentException( "Can't parse " + wkt );
		}
		return Integer.parseInt( sridTokens[1] );
	}
