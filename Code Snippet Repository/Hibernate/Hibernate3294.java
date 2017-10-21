	@Override
	public int size() {
		final Segment<K, V>[] segments = this.segments;
		long sum = 0;
		long check = 0;
		int[] mc = new int[segments.length];
		// Try a few times to get accurate count. On failure due to
		// continuous async changes in table, resort to locking.
		for ( int k = 0; k < RETRIES_BEFORE_LOCK; ++k ) {
			check = 0;
			sum = 0;
			int mcsum = 0;
			for ( int i = 0; i < segments.length; ++i ) {
				sum += segments[i].count;
				mcsum += mc[i] = segments[i].modCount;
			}
			if ( mcsum != 0 ) {
				for ( int i = 0; i < segments.length; ++i ) {
					check += segments[i].count;
					if ( mc[i] != segments[i].modCount ) {
						check = -1; // force retry
						break;
					}
				}
			}
			if ( check == sum ) {
				break;
			}
		}
		if ( check != sum ) { // Resort to locking all segments
			sum = 0;
			for ( int i = 0; i < segments.length; ++i ) {
				segments[i].lock();
			}
			for ( int i = 0; i < segments.length; ++i ) {
				sum += segments[i].count;
			}
			for ( int i = 0; i < segments.length; ++i ) {
				segments[i].unlock();
			}
		}
		if ( sum > Integer.MAX_VALUE ) {
			return Integer.MAX_VALUE;
		}
		else {
			return (int) sum;
		}
	}
