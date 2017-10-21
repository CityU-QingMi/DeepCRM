	@Override
	@SuppressWarnings("")
	public boolean addAll(int index, Collection coll) {
		if ( coll.size()>0 ) {
			write();
			return list.addAll( index,  coll );
		}
		else {
			return false;
		}
	}
