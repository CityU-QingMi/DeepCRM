		V readValueUnderLock(HashEntry<K, V> e) {
			lock();
			try {
				removeStale();
				return e.value();
			}
			finally {
				unlock();
			}
		}
