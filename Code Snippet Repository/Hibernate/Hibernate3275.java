	public BoundedConcurrentHashMap(
			int capacity, int concurrencyLevel,
			Eviction evictionStrategy, EvictionListener<K, V> evictionListener) {
		if ( capacity < 0 || concurrencyLevel <= 0 ) {
			throw new IllegalArgumentException();
		}

		concurrencyLevel = Math.min( capacity / 2, concurrencyLevel ); // concurrencyLevel cannot be > capacity/2
		concurrencyLevel = Math.max( concurrencyLevel, 1 ); // concurrencyLevel cannot be less than 1

		// minimum two elements per segment
		if ( capacity < concurrencyLevel * 2 && capacity != 1 ) {
			throw new IllegalArgumentException( "Maximum capacity has to be at least twice the concurrencyLevel" );
		}

		if ( evictionStrategy == null || evictionListener == null ) {
			throw new IllegalArgumentException();
		}

		if ( concurrencyLevel > MAX_SEGMENTS ) {
			concurrencyLevel = MAX_SEGMENTS;
		}

		// Find power-of-two sizes best matching arguments
		int sshift = 0;
		int ssize = 1;
		while ( ssize < concurrencyLevel ) {
			++sshift;
			ssize <<= 1;
		}
		segmentShift = 32 - sshift;
		segmentMask = ssize - 1;
		this.segments = Segment.newArray( ssize );

		if ( capacity > MAXIMUM_CAPACITY ) {
			capacity = MAXIMUM_CAPACITY;
		}
		int c = capacity / ssize;
		int cap = 1;
		while ( cap < c ) {
			cap <<= 1;
		}

		for ( int i = 0; i < this.segments.length; ++i ) {
			this.segments[i] = new Segment<K, V>( cap, c, DEFAULT_LOAD_FACTOR, evictionStrategy, evictionListener );
		}
	}
