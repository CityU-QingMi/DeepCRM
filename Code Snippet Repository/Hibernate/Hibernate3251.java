	public static String[] join(String[] x, String[] y, boolean[] use) {
		String[] result = new String[x.length + countTrue( use )];
		System.arraycopy( x, 0, result, 0, x.length );
		int k = x.length;
		for ( int i = 0; i < y.length; i++ ) {
			if ( use[i] ) {
				result[k++] = y[i];
			}
		}
		return result;
	}
