	@Override
	public boolean remove(Object key, final Object value) {
		Boolean result = doTask(key, new Task<Boolean>(TaskOption.RESTRUCTURE_AFTER, TaskOption.SKIP_IF_EMPTY) {
			@Override
			protected Boolean execute(@Nullable Reference<K, V> reference, @Nullable Entry<K, V> entry) {
				if (entry != null && ObjectUtils.nullSafeEquals(entry.getValue(), value)) {
					if (reference != null) {
						reference.release();
					}
					return true;
				}
				return false;
			}
		});
		return (result == Boolean.TRUE);
	}
