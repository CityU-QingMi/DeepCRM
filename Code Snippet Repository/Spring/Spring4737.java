	@Nullable
	private V put(final K key, final V value, final boolean overwriteExisting) {
		return doTask(key, new Task<V>(TaskOption.RESTRUCTURE_BEFORE, TaskOption.RESIZE) {
			@Override
			@Nullable
			protected V execute(@Nullable Reference<K, V> reference, @Nullable Entry<K, V> entry, @Nullable Entries entries) {
				if (entry != null) {
					V previousValue = entry.getValue();
					if (overwriteExisting) {
						entry.setValue(value);
					}
					return previousValue;
				}
				Assert.state(entries != null, "No entries segment");
				entries.add(value);
				return null;
			}
		});
	}
