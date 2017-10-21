	public static String determineShortName(String fullName) {
		String result = fullName;

		if ( fullName != null ) {
			final String[] comps = fullName.split( "\\." );
			if ( comps.length == 1 ) {
				return fullName;
			}
			boolean truncate = true;
			for ( int i = 0; i < comps.length; i++ ) {
				final String comp = comps[i];
				final char c = comp.charAt( 0 );
				if ( truncate && Character.isUpperCase( c ) ) {
					truncate = false;
				}
				if ( truncate ) {
					comps[i] = Character.toString( c );
				}
			}
			result = join( comps, '.' );
		}

		return result;
	}
