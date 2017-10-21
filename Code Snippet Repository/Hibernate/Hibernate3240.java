	public static String joinWithQualifierAndSuffix(
			String[] values,
			String qualifier,
			String suffix,
			String deliminator) {
		int length = values.length;
		if ( length == 0 ) {
			return "";
		}
		StringBuilder buf = new StringBuilder( length * ( values[0].length() + suffix.length() ) )
				.append( qualify( qualifier, values[0] ) ).append( suffix );
		for ( int i = 1; i < length; i++ ) {
			buf.append( deliminator ).append( qualify( qualifier, values[i] ) ).append( suffix );
		}
		return buf.toString();
	}
