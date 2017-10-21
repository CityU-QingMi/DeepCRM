	private static String generateAliasRoot(String description) {
		String result = truncate( unqualifyEntityName( description ), ALIAS_TRUNCATE_LENGTH )
				.toLowerCase( Locale.ROOT )
				.replace( '/', '_' ) // entityNames may now include slashes for the representations
				.replace( '$', '_' ); //classname may be an inner class
		result = cleanAlias( result );
		if ( Character.isDigit( result.charAt( result.length() - 1 ) ) ) {
			return result + "x"; //ick!
		}
		else {
			return result;
		}
	}
