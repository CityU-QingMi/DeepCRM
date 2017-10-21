	@Override
	public boolean contains(String name) {
		for ( int low = 0, high = names.length - 1; low <= high; ) {
			final int middle = low + ( ( high - low ) / 2 );
			final int compare = names[middle].compareTo( name );
			if ( compare > 0 ) {
				// bottom half: higher bound in (middle - 1) and insert position in middle
				high = middle - 1;
			}
			else if( compare < 0 ) {
				// top half: lower bound in (middle + 1) and insert position after middle
				low = middle + 1;
			}
			else {
				return true;
			}
		}
		return false;
	}
