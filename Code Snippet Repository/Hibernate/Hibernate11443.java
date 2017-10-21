	@Override
	public boolean contains(Object key) {
		if (!checkValid()) {
			return false;
		}
		Object value = cache.get(key);
		if (value instanceof Tombstone) {
			return false;
		}
		if (value instanceof FutureUpdate) {
			return ((FutureUpdate) value).getValue() != null;
		}
		if (value instanceof VersionedEntry) {
			return ((VersionedEntry) value).getValue() != null;
		}
		return value != null;
	}
