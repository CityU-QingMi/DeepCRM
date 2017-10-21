	public static String[] generateSuffixes(int seed, int length) {
		if ( length == 0 ) {
			return NO_SUFFIX;
		}

		String[] suffixes = new String[length];
		for ( int i = 0; i < length; i++ ) {
			suffixes[i] = (Integer.toString( i + seed ) + "_").intern();
		}
		return suffixes;
	}
