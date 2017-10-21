	@Override
	public boolean remove(Object o) {
		initialize( true );
		final int index = values.indexOf( o );
		if ( index >= 0 ) {
			beforeRemove( index );
			values.remove( index );
			dirty();
			return true;
		}
		else {
			return false;
		}
	}
