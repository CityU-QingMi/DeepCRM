	@Override
	public boolean addAll(int index, Collection c) {
		if ( c.size() > 0 ) {
			for ( Object element : c ) {
				add( index++, element );
			}
			return true;
		}
		else {
			return false;
		}
	}
