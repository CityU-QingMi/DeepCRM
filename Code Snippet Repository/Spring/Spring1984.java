	@Test
	public void putIfAbsent() { // no transactional support for putIfAbsent
		Cache target = new ConcurrentMapCache("testCache");
		Cache cache = new TransactionAwareCacheDecorator(target);

		Object key = new Object();
		assertNull(cache.putIfAbsent(key, "123"));
		assertEquals("123", target.get(key, String.class));
		assertEquals("123", cache.putIfAbsent(key, "456").get());
		assertEquals("123", target.get(key, String.class)); // unchanged
	}
