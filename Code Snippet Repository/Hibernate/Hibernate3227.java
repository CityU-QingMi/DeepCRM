	private static String[] multiply(String[] strings, String placeholder, String[] replacements) {
		String[] results = new String[replacements.length * strings.length];
		int n = 0;
		for ( String replacement : replacements ) {
			for ( String string : strings ) {
				results[n++] = replaceOnce( string, placeholder, replacement );
			}
		}
		return results;
	}
