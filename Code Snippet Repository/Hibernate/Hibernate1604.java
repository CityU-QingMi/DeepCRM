	@Override
	@SuppressWarnings("")
	public Object set(int index, Object value) {
		if (index<0) {
			throw new ArrayIndexOutOfBoundsException("negative index");
		}

		final Object old = isPutQueueEnabled() ? readElementByIndex( index ) : UNKNOWN;

		if ( old==UNKNOWN ) {
			write();
			return list.set( index, value );
		}
		else {
			queueOperation( new Set( index, value, old ) );
			return old;
		}
	}
