	@Override
	@SuppressWarnings("")
	public Object put(Object key, Object value) {
		if ( isPutQueueEnabled() ) {
			final Object old = readElementByIndex( key );
			if ( old != UNKNOWN ) {
				queueOperation( new Put( key, value, old ) );
				return old;
			}
		}
		initialize( true );
		final Object old = map.put( key, value );
		// would be better to use the element-type to determine
		// whether the old and the new are equal here; the problem being
		// we do not necessarily have access to the element type in all
		// cases
		if ( value != old ) {
			dirty();
		}
		return old;
	}
