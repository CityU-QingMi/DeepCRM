	@Override
	@SuppressWarnings("")
	public Object remove(int index) {
		if ( index < 0 ) {
			throw new ArrayIndexOutOfBoundsException( "negative index" );
		}
		final Object old = isPutQueueEnabled() ? readElementByIndex( index ) : UNKNOWN;
		if ( old == UNKNOWN ) {
			write();
			return list.remove( index );
		}
		else {
			queueOperation( new Remove( index, old ) );
			return old;
		}
	}
