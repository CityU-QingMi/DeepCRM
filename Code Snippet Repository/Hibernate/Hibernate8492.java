	public Object deepCopy(Object x) {
		if ( x == null ) {
			return null;
		}
		String[] result = new String[2];
		String[] input = (String[]) x;
		result[0] = input[0];
		result[1] = input[1];
		return result;
	}
