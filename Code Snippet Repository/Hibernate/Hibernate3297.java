		HashEntry<K, V> nextEntry() {
			do {
				if ( nextEntry == null ) {
					throw new NoSuchElementException();
				}

				lastReturned = nextEntry;
				currentKey = lastReturned.key();
				advance();
			} while ( currentKey == null ); // Skip GC'd keys

			return lastReturned;
		}
