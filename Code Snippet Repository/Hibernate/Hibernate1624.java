	@Override
	@SuppressWarnings("")
	public Object remove(Object key) {
		if ( isPutQueueEnabled() ) {
			final Object old = readElementByIndex( key );
			if ( old != UNKNOWN ) {
				queueOperation( new Remove( key, old ) );
				return old;
			}
		}
		// TODO : safe to interpret "map.remove(key) == null" as non-dirty?
		initialize( true );
		if ( map.containsKey( key ) ) {
			dirty();
		}
		return map.remove( key );
	}
