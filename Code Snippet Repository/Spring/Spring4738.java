	@Override
	@Nullable
	public V remove(Object key) {
		return doTask(key, new Task<V>(TaskOption.RESTRUCTURE_AFTER, TaskOption.SKIP_IF_EMPTY) {
			@Override
			@Nullable
			protected V execute(@Nullable Reference<K, V> reference, @Nullable Entry<K, V> entry) {
				if (entry != null) {
					if (reference != null) {
						reference.release();
					}
					return entry.value;
				}
				return null;
			}
		});
	}
