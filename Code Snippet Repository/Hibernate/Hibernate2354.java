	private static int getFromIndex(String queryString) {
		int index = queryString.indexOf( FROM_STRING );

		if ( index < 0 ) {
			return index;
		}

		while ( !parenthesesMatch( queryString.substring( 0, index ) ) ) {
			String subString = queryString.substring( index + FROM_STRING.length() );

			int subIndex = subString.indexOf( FROM_STRING );

			if ( subIndex < 0 ) {
				return subIndex;
			}

			index += FROM_STRING.length() + subIndex;
		}

		return index;
	}
