		@Override
		public boolean contains(@Nullable Object o) {
			if (o != null && o instanceof Map.Entry<?, ?>) {
				Map.Entry<?, ?> entry = (java.util.Map.Entry<?, ?>) o;
				Reference<K, V> reference = ConcurrentReferenceHashMap.this.getReference(entry.getKey(), Restructure.NEVER);
				Entry<K, V> other = (reference != null ? reference.get() : null);
				if (other != null) {
					return ObjectUtils.nullSafeEquals(entry.getValue(), other.getValue());
				}
			}
			return false;
		}
