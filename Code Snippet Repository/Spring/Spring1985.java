	@Test
	public void evictNonTransactional() {
		Cache target = new ConcurrentMapCache("testCache");
		Cache cache = new TransactionAwareCacheDecorator(target);
		Object key = new Object();
		cache.put(key, "123");

		cache.evict(key);
		assertNull(target.get(key));
	}
