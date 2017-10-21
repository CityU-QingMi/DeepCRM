	public static String collapse(String name) {
		if ( name == null ) {
			return null;
		}
		int breakPoint = name.lastIndexOf( '.' );
		if ( breakPoint < 0 ) {
			return name;
		}
		return collapseQualifier(
				name.substring( 0, breakPoint ),
				true
		) + name.substring( breakPoint ); // includes last '.'
	}
