	@Override
	public boolean retainAll(Collection c) {
		initialize( true );
		if ( values.retainAll( c ) ) {
			dirty();
			return true;
		}
		else {
			return false;
		}
	}
