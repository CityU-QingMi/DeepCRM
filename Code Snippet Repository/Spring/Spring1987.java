	@Test
	public void clearNonTransactional() {
		Cache target = new ConcurrentMapCache("testCache");
		Cache cache = new TransactionAwareCacheDecorator(target);
		Object key = new Object();
		cache.put(key, "123");

		cache.clear();
		assertNull(target.get(key));
	}
