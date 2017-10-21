	private void beforeRemove(int index) {
		final Object removedId = identifiers.get( index );
		final int last = values.size()-1;
		for ( int i=index; i<last; i++ ) {
			final Object id = identifiers.get( i+1 );
			if ( id == null ) {
				identifiers.remove( i );
			}
			else {
				identifiers.put( i, id );
			}
		}
		identifiers.put( last, removedId );
	}
