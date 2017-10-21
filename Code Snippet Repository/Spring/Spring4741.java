	@Override
	@Nullable
	public V replace(K key, final V value) {
		return doTask(key, new Task<V>(TaskOption.RESTRUCTURE_BEFORE, TaskOption.SKIP_IF_EMPTY) {
			@Override
			@Nullable
			protected V execute(@Nullable Reference<K, V> reference, @Nullable Entry<K, V> entry) {
				if (entry != null) {
					V previousValue = entry.getValue();
					entry.setValue(value);
					return previousValue;
				}
				return null;
			}
		});
	}
