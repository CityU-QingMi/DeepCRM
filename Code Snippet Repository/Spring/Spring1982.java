	@Test
	public void regularOperationsOnTarget() {
		Cache target = new ConcurrentMapCache("testCache");
		Cache cache = new TransactionAwareCacheDecorator(target);
		assertEquals(target.getName(), cache.getName());
		assertEquals(target.getNativeCache(), cache.getNativeCache());

		Object key = new Object();
		target.put(key, "123");
		assertEquals("123", cache.get(key).get());
		assertEquals("123", cache.get(key, String.class));

		cache.clear();
		assertNull(target.get(key));
	}
