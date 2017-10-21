	public ConcurrentReferenceHashMap(
			int initialCapacity,
			float loadFactor, int concurrencyLevel,
			ReferenceType keyType, ReferenceType valueType,
			EnumSet<Option> options) {
		if ( !( loadFactor > 0 ) || initialCapacity < 0 || concurrencyLevel <= 0 ) {
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

		if ( initialCapacity > MAXIMUM_CAPACITY ) {
			initialCapacity = MAXIMUM_CAPACITY;
		}
		int c = initialCapacity / ssize;
		if ( c * ssize < initialCapacity ) {
			++c;
		}
		int cap = 1;
		while ( cap < c ) {
			cap <<= 1;
		}

		identityComparisons = options != null && options.contains( Option.IDENTITY_COMPARISONS );

		for ( int i = 0; i < this.segments.length; ++i ) {
			this.segments[i] = new Segment<K, V>(
					cap, loadFactor,
					keyType, valueType, identityComparisons
			);
		}
	}
