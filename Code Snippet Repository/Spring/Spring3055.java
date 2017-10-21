	@Test
	public void testDynamicMode() {
		CacheManager cm = new ConcurrentMapCacheManager();
		Cache cache1 = cm.getCache("c1");
		assertTrue(cache1 instanceof ConcurrentMapCache);
		Cache cache1again = cm.getCache("c1");
		assertSame(cache1again, cache1);
		Cache cache2 = cm.getCache("c2");
		assertTrue(cache2 instanceof ConcurrentMapCache);
		Cache cache2again = cm.getCache("c2");
		assertSame(cache2again, cache2);
		Cache cache3 = cm.getCache("c3");
		assertTrue(cache3 instanceof ConcurrentMapCache);
		Cache cache3again = cm.getCache("c3");
		assertSame(cache3again, cache3);

		cache1.put("key1", "value1");
		assertEquals("value1", cache1.get("key1").get());
		cache1.put("key2", 2);
		assertEquals(2, cache1.get("key2").get());
		cache1.put("key3", null);
		assertNull(cache1.get("key3").get());
		cache1.put("key3", null);
		assertNull(cache1.get("key3").get());
		cache1.evict("key3");
		assertNull(cache1.get("key3"));

		assertEquals("value1", cache1.putIfAbsent("key1", "value1x").get());
		assertEquals("value1", cache1.get("key1").get());
		assertEquals(2, cache1.putIfAbsent("key2", 2.1).get());
		assertNull(cache1.putIfAbsent("key3", null));
		assertNull(cache1.get("key3").get());
		assertNull(cache1.putIfAbsent("key3", null).get());
		assertNull(cache1.get("key3").get());
		cache1.evict("key3");
		assertNull(cache1.get("key3"));
	}
