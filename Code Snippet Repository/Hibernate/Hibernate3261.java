		private void fullMiss(Set<HashEntry<K, V>> evicted) {
			// See section 3.3 case 3:
			// "Upon accessing an HIR non-resident block X:
			// This is a miss."

			// This condition is unspecified in the paper, but appears to be
			// necessary.
			if ( owner.size >= owner.maximumSize ) {
				// "We remove the HIR resident block at the front of list Q (it then
				// becomes a non-resident block), and replace it out of the cache."
				LIRSHashEntry<K, V> evictedNode = owner.queueFront();
				evicted.add( evictedNode );
			}

			// "Then we load the requested block X into the freed buffer and place
			// it on the top of stack S."
			boolean inStack = inStack();
			moveToStackTop();

			// "There are two cases for block X:"
			if ( inStack ) {
				// "(1) If X is in stack S, we change its status to LIR and move the
				// LIR block in the bottom of stack S to the end of list Q with its
				// status changed to HIR. A stack pruning is then conducted.
				hot();
				owner.stackBottom().migrateToQueue();
				owner.pruneStack( evicted );
			}
			else {
				// "(2) If X is not in stack S, we leave its status in HIR and place
				// it in the end of list Q."
				cold();
			}
		}
