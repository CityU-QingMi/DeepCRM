	public static String[] unquote(String[] names, Dialect dialect) {
		if ( names == null ) {
			return null;
		}
		String[] unquoted = new String[names.length];
		for ( int i = 0; i < names.length; i++ ) {
			unquoted[i] = unquote( names[i], dialect );
		}
		return unquoted;
	}
