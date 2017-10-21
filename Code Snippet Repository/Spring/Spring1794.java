	@SuppressWarnings("")
	@Override
	@Nullable
	public <T> T get(Object key, Callable<T> valueLoader) {
		Element element = lookup(key);
		if (element != null) {
			return (T) element.getObjectValue();
		}
		else {
			this.cache.acquireWriteLockOnKey(key);
			try {
				element = lookup(key); // One more attempt with the write lock
				if (element != null) {
					return (T) element.getObjectValue();
				}
				else {
					return loadValue(key, valueLoader);
				}
			}
			finally {
				this.cache.releaseWriteLockOnKey(key);
			}
		}

	}
