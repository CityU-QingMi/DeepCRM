	@Override
	@SuppressWarnings("")
	public boolean addAll(Collection coll) {
		if ( coll.size() > 0 ) {
			initialize( true );
			if ( set.addAll( coll ) ) {
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
