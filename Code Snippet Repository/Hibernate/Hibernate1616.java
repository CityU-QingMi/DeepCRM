	@Override
	@SuppressWarnings("")
	public boolean retainAll(Collection coll) {
		initialize( true );
		if ( list.retainAll( coll ) ) {
			dirty();
			return true;
		}
		else {
			return false;
		}
	}
