	@Override
	@SuppressWarnings("")
	public boolean removeAll(Collection c) {
		if ( c.size()>0 ) {
			initialize( true );
			if ( bag.removeAll( c ) ) {
				dirty();
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
