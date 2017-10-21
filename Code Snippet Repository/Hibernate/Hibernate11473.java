		@Override
		public CloseableIterator<T> iterator() {
			final CloseableIterator<CacheEntry<K, V>> entryIterator = entryIterable.iterator();
			return new CloseableIterator<T>() {
				@Override
				public void close() {
					entryIterator.close();
				}

				@Override
				public boolean hasNext() {
					return entryIterator.hasNext();
				}

				@Override
				public T next() {
					return selector.apply(entryIterator.next());
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException( "remove() not supported" );
				}
			};
		}
