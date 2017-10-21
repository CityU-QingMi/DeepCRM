	@Override
	public boolean isEmpty() {
		final Segment<K, V>[] segments = this.segments;
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
		int[] mc = new int[segments.length];
		int mcsum = 0;
		for ( int i = 0; i < segments.length; ++i ) {
			if ( segments[i].count != 0 ) {
				return false;
			}
			else {
				mcsum += mc[i] = segments[i].modCount;
			}
		}
		// If mcsum happens to be zero, then we know we got a snapshot
		// before any modifications at all were made.  This is
		// probably common enough to bother tracking.
		if ( mcsum != 0 ) {
			for ( int i = 0; i < segments.length; ++i ) {
				if ( segments[i].count != 0 || mc[i] != segments[i].modCount ) {
					return false;
				}
			}
		}
		return true;
	}
