	@Override
	public Object get(SharedSessionContractImplementor session, Object key, long txTimestamp) throws CacheException {
		if (txTimestamp < region.getLastRegionInvalidation() ) {
			return null;
		}
		Object value = cache.get(key);
		if (value instanceof Tombstone) {
			return null;
		}
		else if (value instanceof FutureUpdate) {
			return ((FutureUpdate) value).getValue();
		}
		else {
			return value;
		}
	}
