	public static String[] suffix(String[] columns, String suffix) {
		if ( suffix == null ) {
			return columns;
		}
		String[] qualified = new String[columns.length];
		for ( int i = 0; i < columns.length; i++ ) {
			qualified[i] = suffix( columns[i], suffix );
		}
		return qualified;
	}
