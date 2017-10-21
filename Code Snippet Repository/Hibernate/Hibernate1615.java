	@Override
	@SuppressWarnings("")
	public boolean removeAll(Collection coll) {
		if ( coll.size()>0 ) {
			initialize( true );
			if ( list.removeAll( coll ) ) {
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
