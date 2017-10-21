	@Override
	@SuppressWarnings("")
	public boolean addAll(int i, Collection c) {
		if ( c.size() > 0 ) {
			write();
			return bag.addAll( i, c );
		}
		else {
			return false;
		}
	}
