		@Nullable
		public <T> T doTask(final int hash, final Object key, final Task<T> task) {
			boolean resize = task.hasOption(TaskOption.RESIZE);
			if (task.hasOption(TaskOption.RESTRUCTURE_BEFORE)) {
				restructureIfNecessary(resize);
			}
			if (task.hasOption(TaskOption.SKIP_IF_EMPTY) && this.count == 0) {
				return task.execute(null, null, null);
			}
			lock();
			try {
				final int index = getIndex(hash, this.references);
				final Reference<K, V> head = this.references[index];
				Reference<K, V> reference = findInChain(head, key, hash);
				Entry<K, V> entry = (reference != null ? reference.get() : null);
				Entries entries = new Entries() {
					@Override
					public void add(V value) {
						@SuppressWarnings("unchecked")
						Entry<K, V> newEntry = new Entry<>((K) key, value);
						Reference<K, V> newReference = Segment.this.referenceManager.createReference(newEntry, hash, head);
						Segment.this.references[index] = newReference;
						Segment.this.count++;
					}
				};
				return task.execute(reference, entry, entries);
			}
			finally {
				unlock();
				if (task.hasOption(TaskOption.RESTRUCTURE_AFTER)) {
					restructureIfNecessary(resize);
				}
			}
		}
