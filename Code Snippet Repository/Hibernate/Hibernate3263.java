		private V remove() {
			boolean wasHot = ( state == Recency.LIR_RESIDENT );
			V result = value;
			LIRSHashEntry<K, V> end = owner != null ? owner.queueEnd() : null;
			evict();

			// attempt to maintain a constant number of hot entries
			if ( wasHot ) {
				if ( end != null ) {
					end.migrateToStack();
				}
			}

			return result;
		}
