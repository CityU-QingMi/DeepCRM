	@Override
	public Object indexOf(Object array, Object element) {
		int length = Array.getLength(array);
		for ( int i=0; i<length; i++ ) {
			//TODO: proxies!
			if ( Array.get(array, i)==element ) {
				return i;
			}
		}
		return null;
	}
