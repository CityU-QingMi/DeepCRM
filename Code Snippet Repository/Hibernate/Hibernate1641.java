	@Override
	@SuppressWarnings("")
	public boolean retainAll(Collection coll) {
		initialize( true );
		if ( set.retainAll( coll ) ) {
			dirty();
			return true;
		}
		else {
			return false;
		}
	}
