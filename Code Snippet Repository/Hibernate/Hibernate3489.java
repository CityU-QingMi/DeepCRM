	protected static String mergeOrderings(String ordering1, String ordering2) {
		if ( ordering1.length() == 0 ) {
			return ordering2;
		}
		else if ( ordering2.length() == 0 ) {
			return ordering1;
		}
		else {
			return ordering1 + ", " + ordering2;
		}
	}
