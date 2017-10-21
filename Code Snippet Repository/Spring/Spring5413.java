		@Override
		protected ReferenceManager createReferenceManager() {
			return new ReferenceManager() {
				@Override
				public Reference<K, V> createReference(Entry<K, V> entry, int hash,
						@Nullable Reference<K, V> next) {
					if (TestWeakConcurrentCache.this.disableTestHooks) {
						return super.createReference(entry, hash, next);
					}
					return new MockReference<>(entry, hash, next, TestWeakConcurrentCache.this.queue);
				}
				@Override
				public Reference<K, V> pollForPurge() {
					if (TestWeakConcurrentCache.this.disableTestHooks) {
						return super.pollForPurge();
					}
					return TestWeakConcurrentCache.this.queue.isEmpty() ? null : TestWeakConcurrentCache.this.queue.removeFirst();
				}
			};
		}
