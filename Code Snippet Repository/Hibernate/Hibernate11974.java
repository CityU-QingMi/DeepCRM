	static public String getWkt(String wkt) {
		String[] tokens = wkt.split( ";" );
		if ( tokens.length > 1 ) {
			return tokens[1];
		}
		else {
			return wkt;
		}

	}
