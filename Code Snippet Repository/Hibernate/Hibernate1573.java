	@Override
	public boolean remove(Object o) {
		initialize( true );
		if ( bag.remove( o ) ) {
			dirty();
			return true;
		}
		else {
			return false;
		}
	}
