	@Override
	@CachePut(afterInvocation = false)
	public void earlyPut(String id, @CacheValue Object value) {
		Object key = SimpleKeyGenerator.generateKey(id);
		Cache.ValueWrapper valueWrapper = defaultCache.get(key);
		if (valueWrapper == null) {
			throw new AssertionError("Excepted value to be put in cache with key " + key);
		}
		Object actual = valueWrapper.get();
		if (value != actual) { // instance check on purpose
			throw new AssertionError("Wrong value set in cache with key " + key + ". " +
					"Expected=" + value + ", but got=" + actual);
		}
	}
