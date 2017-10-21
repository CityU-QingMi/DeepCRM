	@Test
	public void testStaticMode() {
		ConcurrentMapCacheManager cm = new ConcurrentMapCacheManager("c1", "c2");
		Cache cache1 = cm.getCache("c1");
		assertTrue(cache1 instanceof ConcurrentMapCache);
		Cache cache1again = cm.getCache("c1");
		assertSame(cache1again, cache1);
		Cache cache2 = cm.getCache("c2");
		assertTrue(cache2 instanceof ConcurrentMapCache);
		Cache cache2again = cm.getCache("c2");
		assertSame(cache2again, cache2);
		Cache cache3 = cm.getCache("c3");
		assertNull(cache3);

		cache1.put("key1", "value1");
		assertEquals("value1", cache1.get("key1").get());
		cache1.put("key2", 2);
		assertEquals(2, cache1.get("key2").get());
		cache1.put("key3", null);
		assertNull(cache1.get("key3").get());
		cache1.evict("key3");
		assertNull(cache1.get("key3"));

		cm.setAllowNullValues(false);
		Cache cache1x = cm.getCache("c1");
		assertTrue(cache1x instanceof ConcurrentMapCache);
		assertTrue(cache1x != cache1);
		Cache cache2x = cm.getCache("c2");
		assertTrue(cache2x instanceof ConcurrentMapCache);
		assertTrue(cache2x != cache2);
		Cache cache3x = cm.getCache("c3");
		assertNull(cache3x);

		cache1x.put("key1", "value1");
		assertEquals("value1", cache1x.get("key1").get());
		cache1x.put("key2", 2);
		assertEquals(2, cache1x.get("key2").get());

		cm.setAllowNullValues(true);
		Cache cache1y = cm.getCache("c1");

		cache1y.put("key3", null);
		assertNull(cache1y.get("key3").get());
		cache1y.evict("key3");
		assertNull(cache1y.get("key3"));
	}
