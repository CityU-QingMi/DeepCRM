	public static String[] qualifyIfNot(String prefix, String[] names) {
		if ( prefix == null ) {
			return names;
		}
		int len = names.length;
		String[] qualified = new String[len];
		for ( int i = 0; i < len; i++ ) {
			if ( names[i].indexOf( '.' ) < 0 ) {
				qualified[i] = qualify( prefix, names[i] );
			}
			else {
				qualified[i] = names[i];
			}
		}
		return qualified;
	}
