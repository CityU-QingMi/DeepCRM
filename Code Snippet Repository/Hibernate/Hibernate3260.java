		private Set<HashEntry<K, V>> miss() {
			Set<HashEntry<K, V>> evicted = Collections.emptySet();
			if ( owner.hotSize < owner.maximumHotSize ) {
				warmupMiss();
			}
			else {
				evicted = new HashSet<HashEntry<K, V>>();
				fullMiss( evicted );
			}

			// now the missed item is in the cache
			owner.size++;
			return evicted;
		}
