	private String cleanRestrictions(String restrictions) {
		restrictions = restrictions.trim();
		if ( restrictions.startsWith( "and " ) ) {
			restrictions = restrictions.substring( 4 );
		}
		if ( restrictions.endsWith( " and" ) ) {
			restrictions = restrictions.substring( 0, restrictions.length()-4 );
		}

		return restrictions;
	}
