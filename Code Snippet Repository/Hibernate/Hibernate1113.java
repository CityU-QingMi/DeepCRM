	@Override
	public void add(String name) {
		if ( suspended ) {
			return;
		}
		// we do a binary search: even if we don't find the name at least we get the position to insert into the array
		int insert = 0;
		for ( int low = 0, high = names.length - 1; low <= high; ) {
			final int middle = low + ( ( high - low ) / 2 );
			final int compare = names[middle].compareTo( name );
			if ( compare > 0 ) {
				// bottom half: higher bound in (middle - 1) and insert position in middle
				high = middle - 1;
				insert = middle;
			}
			else if( compare < 0 ) {
				// top half: lower bound in (middle + 1) and insert position after middle
				insert = low = middle + 1;
			}
			else {
				return;
			}
		}
		final String[] newNames = new String[names.length + 1];
		System.arraycopy( names, 0, newNames, 0, insert );
		System.arraycopy( names, insert, newNames, insert + 1, names.length - insert );
		newNames[insert] = name;
		names = newNames;
	}
