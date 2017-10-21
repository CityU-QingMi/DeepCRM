	public static boolean arraysEqual(Object[] array1, Object[] array2) {
		if ( array1 == null ) {
			return array2 == null;
		}
		if ( array2 == null || array1.length != array2.length ) {
			return false;
		}
		for ( int i = 0; i < array1.length; ++i ) {
			if ( array1[i] != null ? !array1[i].equals( array2[i] ) : array2[i] != null ) {
				return false;
			}
		}
		return true;
	}
