		private void hotHit(Set<HashEntry<K, V>> evicted) {
			// See section 3.3 case 1:
			// "Upon accessing an LIR block X:
			// This access is guaranteed to be a hit in the cache."

			// "We move it to the top of stack S."
			boolean onBottom = ( owner.stackBottom() == this );
			moveToStackTop();

			// "If the LIR block is originally located in the bottom of the stack,
			// we conduct a stack pruning."
			if ( onBottom ) {
				owner.pruneStack( evicted );
			}
		}
