		private void coldHit(Set<HashEntry<K, V>> evicted) {
			// See section 3.3 case 2:
			// "Upon accessing an HIR resident block X:
			// This is a hit in the cache."

			// "We move it to the top of stack S."
			boolean inStack = inStack();
			moveToStackTop();

			// "There are two cases for block X:"
			if ( inStack ) {
				// "(1) If X is in the stack S, we change its status to LIR."
				hot();

				// "This block is also removed from list Q."
				removeFromQueue();

				// "The LIR block in the bottom of S is moved to the end of list Q
				// with its status changed to HIR."
				owner.stackBottom().migrateToQueue();

				// "A stack pruning is then conducted."
				owner.pruneStack( evicted );
			}
			else {
				// "(2) If X is not in stack S, we leave its status in HIR and move
				// it to the end of list Q."
				moveToQueueEnd();
			}
		}
