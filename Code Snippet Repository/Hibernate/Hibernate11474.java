		@Override
		public String toString() {
			CloseableIterator<CacheEntry<K, V>> it = entryIterable.iterator();
			try {
				if (!it.hasNext()) {
					return "[]";
				}

				StringBuilder sb = new StringBuilder();
				sb.append('[');
				for (; ; ) {
					CacheEntry<K, V> entry = it.next();
					sb.append(selector.apply(entry));
					if (!it.hasNext()) {
						return sb.append(']').toString();
					}
					sb.append(',').append(' ');
				}
			}
			finally {
				it.close();
			}
		}
