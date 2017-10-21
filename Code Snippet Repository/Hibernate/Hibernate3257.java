	@Deprecated
	public static boolean isEquals(byte[] b1, byte[] b2) {
		if ( b1 == b2 ) {
			return true;
		}
		if ( b1 == null || b2 == null ) {
			return false;
		}
		int length = b1.length;
		if ( length != b2.length ) {
			return false;
		}
		for ( int index = 0; index < length; index++ ) {
			if ( !( b1[index] == b2[index] ) ) {
				return false;
			}
		}
		return true;
	}
