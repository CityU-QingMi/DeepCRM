	@Override
	public boolean addAll(Collection c) {
		if ( c.size()> 0 ) {
			write();
			return values.addAll( c );
		}
		else {
			return false;
		}
	}
