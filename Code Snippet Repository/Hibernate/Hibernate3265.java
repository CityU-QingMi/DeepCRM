		private void pruneStack(Set<HashEntry<K, V>> evicted) {
			// See section 3.3:
			// "We define an operation called "stack pruning" on the LIRS
			// stack S, which removes the HIR blocks in the bottom of
			// the stack until an LIR block sits in the stack bottom. This
			// operation serves for two purposes: (1) We ensure the block in
			// the bottom of the stack always belongs to the LIR block set.
			// (2) After the LIR block in the bottom is removed, those HIR
			// blocks contiguously located above it will not have chances to
			// change their status from HIR to LIR, because their recencies
			// are larger than the new maximum recency of LIR blocks."
			LIRSHashEntry<K, V> bottom = stackBottom();
			while ( bottom != null && bottom.state != Recency.LIR_RESIDENT ) {
				bottom.removeFromStack();
				if ( bottom.state == Recency.HIR_NONRESIDENT ) {
					evicted.add( bottom );
				}
				bottom = stackBottom();
			}
		}
